package com.ripstech.apiconnector2;

import com.ripstech.apiconnector2.config.HttpClientConfig;
import com.ripstech.apiconnector2.connector.path.application.ScanWoIdPath;
import com.ripstech.apiconnector2.connector.path.application.scan.IssueWoIdPath;
import com.ripstech.apiconnector2.entity.receive.application.scan.Issue;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Type;
import com.ripstech.apiconnector2.entity.send.LogSend;
import com.ripstech.apiconnector2.entity.send.OrganisationSend;
import com.ripstech.apiconnector2.entity.send.SettingSend;
import com.ripstech.apiconnector2.entity.send.application.ScanPost;
import com.ripstech.apiconnector2.entity.send.application.ScanSend;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.queryparameter.Filter;

import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.function.BiConsumer;

import static com.ripstech.apiconnector2.service.queryparameter.JsonFilter.equal;
import static com.ripstech.apiconnector2.service.queryparameter.JsonFilter.or;

public class Main {

	public static void main(String... args) throws IOException, ApiException {

		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");
		Properties properties = new Properties();
		properties.load(inputStream);

		Api api = new Api.Builder(properties.getProperty("api.baseuri"))
				.withHttpClientConfig(new HttpClientConfig().setUserAgent("rpi api connector/2.0.0"))
				          .withXPassword(properties.getProperty("api.username"), properties.getProperty("api.password"))
				          .build();

		api.application(2)
				.scan(2)
				.issues()
				.get(new Filter().json(or(
						equal("typeCategory", "client_vulnerability"),
						equal("typeCategory", "server_vulnerability"))))
				.process(issues -> issues.stream()
						                   .map(Issue::getType)
						                   .map(Type::getCategory)
						                   .forEach(System.out::println),
				         printError());

		api.status().get().process(System.out::println, printError());

		api.sources().get().process(System.out::println, printError());

		api.licenses().get().process(System.out::println, printError());

		api.applications().scans().stats().get(new Filter().limit(1)).process(applicationScanStats -> {
			System.out.println(applicationScanStats.getIssueDepths().size());
			System.out.println(applicationScanStats.getIssueSeverities().size());
			System.out.println(applicationScanStats.getIssueTypes().size());
		}, printError());

		api.logs().get(new Filter().limit(5)).process(System.out::println, printError());
		api.logs().post(LogSend.createPost("test log", 5)).process(log -> {
			api.logs().delete(Filter.empty().isEqual("text", log.getText())).ifNotOk(printError());
			api.logs().get(log.getId()).process(System.out::println, printError());
		}, printError());

		api.activities().get(new Filter().limit(5)).process(System.out::println, printError());

		api.settings().get().process(System.out::println, printError());
		api.settings().put("test123", SettingSend.createPut("abc")).process(
				setting -> api.settings().delete(setting.getKey()),
				printError());
		api.settings().put("test321", "cab").process(
				setting -> api.settings().delete(new Filter().isEqual("value", setting.getValue())),
				printError());

		api.organisations().get().process(System.out::println, printError());
		api.organisations()
				.post(OrganisationSend.createPost("test orga").setTrialIssueTypes(Arrays.asList("123", "234")))
				.process(organisation -> {
					System.out.println(organisation);
					api.organisations().patch(organisation.getId(), OrganisationSend.createPatch()
							                                                .setValidUntil(OffsetDateTime.now()
									                                                               .plusMonths(1))
							                                                .setTrialIssueTypes(null))
							.process(System.out::println, printError());
					api.organisations().delete(organisation.getId()).ifNotOk(printError());
		}, printError());

		ScanWoIdPath scanPath = api.applications().scans();
		IssueWoIdPath issuePath = scanPath.issues();
		issuePath.commentsAll().get(new Filter().limit(5)).process(System.out::println, printError());
		issuePath.originTypes().get().process(System.out::println, printError());
		issuePath.reviewTypes().get().process(System.out::println, printError());
		scanPath.stats().get(new Filter().limit(5)).process(System.out::println, printError());

			api.applications().get().ifOk(apps -> apps.stream().findFirst().ifPresent(app -> {
				api.application(app.getId()).scans().get().ifOk(scans -> scans.stream().findFirst().ifPresent(scan -> {
					api.application(app.getId())
							.scans()
							.patch(scan.getId(), ScanSend.createPatch().setTags(Arrays.asList("TAG1", "TAG2")))
							.ifOk(newScan -> System.out.println(newScan.getTags()));
					api.application(app.getId())
							.scans()
							.patch(scan.getId(), ScanSend.createPatch().setTags(Collections.singletonList("TAG3")))
							.ifOk(newScan -> System.out.println(newScan.getTags()));
					api.application(app.getId())
							.scans()
							.patch(scan.getId(), ScanSend.createPatch().setTags(null))
							.ifOk(newScan -> System.out.println(newScan.getTags()));
					api.application(app.getId())
							.scans()
							.post(ScanPost.createPost(ScanPost.ScanSub.createPost("123").setAnalysisDepth(5)))
							.process(System.out::print, printError());
				}));
			}));

	}

	private static BiConsumer<HttpStatus, String> printError() {
		return (status, msg) -> System.err.println(status + " : " + msg);
	}

}
