package com.ripstech.apiconnector2;

import com.ripstech.api.entity.send.LogSend;
import com.ripstech.api.entity.send.OrganizationSend;
import com.ripstech.api.entity.send.SettingSend;
import com.ripstech.api.entity.send.application.ScanSend;
import com.ripstech.apiconnector2.config.HttpClientConfig;
import com.ripstech.apiconnector2.connector.path.application.ScanWoIdPath;
import com.ripstech.apiconnector2.connector.path.application.scan.IssueWoIdPath;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.application.ScanService;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import com.ripstech.apiconnector2.service.queryparameter.JsonFilter;

import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.BiConsumer;

import static com.ripstech.apiconnector2.service.queryparameter.JsonFilter.equal;

public class Main {

	public static void main(String... args) throws IOException, ApiException {

		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");
		Properties properties = new Properties();
		properties.load(inputStream);

		Api api = new Api.Builder(properties.getProperty("api.baseuri"))
				.withHttpClientConfig(new HttpClientConfig().setUserAgent("rpi api connector/2.0.0"))
				          .withXPassword(properties.getProperty("api.username"), properties.getProperty("api.password"))
				          .build();

		System.out.println(api.application(1L)
				                   .scans()
				                   .get(new Filter().offset(0))
				                   .map(List::size)
				                   .orElse(0));
		System.out.println(api.application(1L)
				                   .scans()
				                   .get()
				                   .map(List::size)
				                   .orElse(0));

		api.applications().get(new Filter().orderBy("name", "createdAt")).process(System.out::println, printError());

		api.status().get(new Filter().json(JsonFilter.or(equal("name", "hallo")))).process(System.out::println, printError());

		api.sources().get().process(System.out::println, printError());

		api.licenses().get().process(System.out::println, printError());

		api.applications().scans().stats().get(new Filter().limit(1)).process(applicationScanStats -> {
			System.out.println(applicationScanStats.getIssueDepths().size());
			System.out.println(applicationScanStats.getIssueSeverities().size());
			System.out.println(applicationScanStats.getIssueTypes().size());
		}, printError());

		api.logs().get(new Filter().limit(5)).process(System.out::println, printError());
		api.logs().post(new LogSend.Post(5, "test log")).process(log -> {
			api.logs().delete(new Filter().json(equal("text", log.getText()))).ifNotOk(printError());
			api.logs().get(log.getId()).process(System.out::println, printError());
		}, printError());

		api.activities().get(new Filter().limit(5)).process(System.out::println, printError());

		api.settings().get().process(System.out::println, printError());
		api.settings().put("test123", new SettingSend.Put("abc")).process(
				setting -> api.settings().delete(setting.getKey()),
				printError());
		api.settings().put("test321", "cab").process(
				setting -> api.settings().delete(new Filter(equal("value", setting.getValue()))),
				printError());

		api.organizations().get().process(System.out::println, printError());
		api.organizations()
				.post(new OrganizationSend.Post("test orga").setTrialIssueTypes(Arrays.asList(123L, 234L)))
				.process(organisation -> {
					System.out.println(organisation);
					api.organizations().patch(organisation.getId(), new OrganizationSend.Patch()
							                                                .setValidUntil(OffsetDateTime.now()
									                                                               .plusMonths(1))
							                                                .setTrialIssueTypes(null))
							.process(System.out::println, printError());
					api.organizations().delete(organisation.getId()).ifNotOk(printError());
		}, printError());

		ScanWoIdPath scanPath = api.applications().scans();
		IssueWoIdPath issuePath = scanPath.issues();
		issuePath.originTypes().get().process(System.out::println, printError());
		issuePath.reviewTypes().get().process(System.out::println, printError());
		scanPath.stats().get(new Filter().limit(5)).process(System.out::println, printError());

			api.applications().get().ifOk(apps -> apps.stream().findFirst().ifPresent(app -> {
				api.application(app.getId()).scans().get().ifOk(scans -> scans.stream().findFirst().ifPresent(scan -> {
					api.application(app.getId())
							.scans()
							.post(new ScanService.ScanSendPost(new ScanSend.Post("123").setAnalysisDepth(5)))
							.process(System.out::print, printError());
				}));
			}));

	}

	private static BiConsumer<HttpStatus, String> printError() {
		return (status, msg) -> System.err.println(status + " : " + msg);
	}

}
