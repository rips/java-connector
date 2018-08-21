package com.ripstech.apiconnector2;

import com.ripstech.apiconnector2.entity.receive.application.custom.Setting;
import com.ripstech.apiconnector2.entity.receive.application.scan.Issue;
import com.ripstech.apiconnector2.entity.receive.application.scan.Php;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.application.ScanService;
import com.ripstech.apiconnector2.service.application.scan.IssueService;
import com.ripstech.apiconnector2.service.queryparameter.Filter;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
                scanService.get(scanId).process(applicationScan -> {
                    if (percent.get() != applicationScan.getPercent()) {
                        logConsumer.accept(String.format(
                                "Scan Status: %d%% (%s)",
                                applicationScan.getPercent(),
                                applicationScan.getPhaseName().getDescription()
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
                scanService.get(scanId).process(applicationScan -> {
                    if (percent.get() != applicationScan.getPercent()) {
                        logConsumer.accept(String.format(
                                "Scan Status: %d%% (%s)",
                                applicationScan.getPercent(),
                                applicationScan.getPhaseName().getDescription()
                        ));
                    }
                    percent.set(applicationScan.getPercent());
                    issueService.get(filter.greaterThan("id", highestIssueId.get()))
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

    public static String getPhpVersion(Php php) {
        if(php == null)
            return "";
        StringBuilder phpVersion = new StringBuilder();
        resolve(php::getMajorVersion).ifPresent(major -> {
            phpVersion.append(major);
            resolve(php::getMinorVersion).ifPresent(minor -> {
                phpVersion.append('.').append(minor);
                resolve(php::getReleaseVersion).ifPresent(release -> phpVersion.append('.').append(release));
            });
        });
        return phpVersion.toString();
    }

    public static String getPhpVersion(Setting setting) {
        if(setting == null || setting.getPhp() == null)
            return "";
        return getPhpVersion(setting.getPhp());
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
