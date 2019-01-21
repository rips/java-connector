package com.ripstech.api.connector;

import com.ripstech.api.entity.receive.application.scan.Issue;
import com.ripstech.api.entity.receive.application.scan.issue.Type;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.application.ScanService;
import com.ripstech.api.connector.service.application.scan.IssueService;
import com.ripstech.api.connector.service.queryparameter.Filter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.ripstech.api.connector.service.queryparameter.JsonFilter.greaterThan;

@SuppressWarnings("unused")
public class ApiUtils {

    public static Future<List<Issue>> getScanIssues(ScanService scanService,
                                                    IssueService issueService,
                                                    long scanId,
                                                    Filter filter,
                                                    Consumer<String> logConsumer,
                                                    int pollInterval,
                                                    TimeUnit pollTimeUnit) {
        CompletableFuture<List<Issue>> completableIssues = new CompletableFuture<>();
        AtomicInteger tries = new AtomicInteger(0);
        AtomicInteger percent = new AtomicInteger(-1);

        ScheduledFuture<?> scheduledFuture = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->
                scanService.get(scanId, new Filter().select("percent", "phase")).process(applicationScan -> {
                    if (percent.get() != applicationScan.getPercent()) {
                        logConsumer.accept(String.format(
                                "Scan Status: %d%% (%s)",
                                applicationScan.getPercent(),
                                Phase.getById(applicationScan.getPhase()).getDescription()
                        ));
                    }
                    percent.set(applicationScan.getPercent());
                    if(applicationScan.getPercent() >= 100) {
                        issueService.get(filter).process(
                                completableIssues::complete,
                                (httpStatus, s) -> completableIssues.completeExceptionally(new ApiException(httpStatus, s))
                        );
                    }
                }, (httpStatus, s) -> {
                    logConsumer.accept("Error during scan polling: " + new ApiException(httpStatus, s));
                    if (tries.incrementAndGet() > 10) {
                        completableIssues.completeExceptionally(new ApiException(httpStatus, s));
                    }
                }), 0, pollInterval, pollTimeUnit);
        if(scheduledFuture.isCancelled()) {
            completableIssues.completeExceptionally(new ApiException("Cannot get Issues!"));
        }
        return completableIssues.whenComplete((i, t) -> scheduledFuture.cancel(true));
    }

    public static Future<List<Issue>> getScanIssues(ScanService scanService,
                                                    IssueService issueService,
                                                    long scanId,
                                                    Filter filter,
                                                    Consumer<String> logConsumer) {
        return getScanIssues(scanService, issueService, scanId, filter, logConsumer, 10, TimeUnit.SECONDS);
    }

    public static Future<List<Issue>> getScanIssues(ScanService scanService,
                                                    IssueService issueService,
                                                    long scanId,
                                                    Consumer<String> logConsumer) {
        return getScanIssues(scanService, issueService, scanId, Filter.empty(), logConsumer);
    }

    public static void processScanIssuesAsync(ScanService scanService,
                                              IssueService issueService,
                                              long scanId,
                                              Filter filter,
                                              Consumer<String> logConsumer,
                                              int pollInterval,
                                              TimeUnit pollTimeUnit,
                                              int timeout,
                                              TimeUnit timeoutTimeUnit,
                                              Consumer<Issue> scanIssueProcessor)
            throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        AtomicInteger tries = new AtomicInteger(0);
        AtomicInteger percent = new AtomicInteger(-1);
        AtomicLong highestIssueId = new AtomicLong(-1);

        ScheduledFuture<?> scheduledFuture = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->
                scanService.get(scanId, new Filter().select("percent", "phase")).process(applicationScan -> {
                    if (percent.get() != applicationScan.getPercent()) {
                        logConsumer.accept(String.format(
                                "Scan Status: %d%% (%s)",
                                applicationScan.getPercent(),
                                Phase.getById(applicationScan.getPhase()).getDescription()
                        ));
                    }
                    percent.set(applicationScan.getPercent());
                    issueService.get(filter.json(greaterThan("id", highestIssueId.get())))
                            .process(applicationScanIssues -> {
                                        applicationScanIssues.stream()
                                                .map(Issue::getId)
                                                .max(Long::compareTo)
                                                .ifPresent(highestIssueId::set);
                                        applicationScanIssues.forEach(scanIssueProcessor);
                                        if (percent.get() >= 100) {
                                            completableFuture.complete(null); //Void
                                        }
                                    }, (httpStatus, s) ->
                                    completableFuture.completeExceptionally(new ApiException(httpStatus, s))
                            );
                }, (httpStatus, s) -> {
                    logConsumer.accept("Error during scan polling: " + new ApiException(httpStatus, s));
                    if (tries.incrementAndGet() > 10) {
                        completableFuture.completeExceptionally(new ApiException(httpStatus, s));
                    }
                }), 0, pollInterval, pollTimeUnit);
        completableFuture.get(timeout, timeoutTimeUnit);
        scheduledFuture.cancel(true);
    }

    public static void processScanIssuesAsync(ScanService scanService,
                                              IssueService issueService,
                                              long scanId,
                                              Filter filter,
                                              Consumer<String> logConsumer,
                                              Consumer<Issue> scanIssueProcessor)
            throws InterruptedException, ExecutionException, TimeoutException {
        processScanIssuesAsync(
                scanService,
                issueService,
                scanId,
                filter,
                logConsumer,
                10,
                TimeUnit.SECONDS,
                5,
                TimeUnit.HOURS,
                scanIssueProcessor
                              );
    }

    @SuppressWarnings("unused")
    public static void processScanIssuesAsync(ScanService scanService,
                                              IssueService issueService,
                                              long scanId,
                                              Consumer<String> logConsumer,
                                              Consumer<Issue> scanIssueProcessor)
            throws InterruptedException, ExecutionException, TimeoutException {
        processScanIssuesAsync(
                scanService,
                issueService,
                scanId,
                Filter.empty(),
                logConsumer,
                scanIssueProcessor
        );
    }

    @NotNull
    public static Map<Long, Integer> getIssueTypeSeverities(@NotNull final Api api) throws ApiException {
        return api.applications()
                       .scans()
                       .issues()
                       .types()
                       .get(new Filter().select("id", "severity"))
                       .orThrow(ApiException::new)
                       .stream()
                       .map(types -> new AbstractMap.SimpleImmutableEntry<>(
                               types.getId(),
                               types.getSeverity()))
                       .collect(Collectors.toMap(
                               AbstractMap.SimpleImmutableEntry::getKey,
                               AbstractMap.SimpleImmutableEntry::getValue));
    }

    public static Map<Long, Type> getIssueTypeSeverities(@NotNull final Api api, String... selects) throws ApiException {
        final Set<String> sendSelect = new HashSet<>(Arrays.asList(selects));
        sendSelect.add("id");
        return api.applications()
                       .scans()
                       .issues()
                       .types()
                       .get(new Filter().select(sendSelect))
                       .orThrow(ApiException::new)
                       .stream()
                       .map(types -> new AbstractMap.SimpleImmutableEntry<>(
                               types.getId(),
                               types))
                       .collect(Collectors.toMap(
                               AbstractMap.SimpleImmutableEntry::getKey,
                               AbstractMap.SimpleImmutableEntry::getValue));
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            return Optional.empty();
        }
    }


}
