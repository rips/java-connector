package com.ripstech.apiconnector2;

import com.ripstech.apiconnector2.authorization.HeaderAuthenticator;
import com.ripstech.apiconnector2.authorization.OAuth2;
import com.ripstech.apiconnector2.authorization.XPassword;
import com.ripstech.apiconnector2.config.HttpClientConfig;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.*;
import com.ripstech.apiconnector2.service.application.*;
import com.ripstech.apiconnector2.service.application.custom.IgnoreService;
import com.ripstech.apiconnector2.service.application.custom.SanitiserService;
import com.ripstech.apiconnector2.service.application.custom.ValidatorService;
import com.ripstech.apiconnector2.service.application.scan.*;
import com.ripstech.apiconnector2.service.application.scan.SourceService;
import com.ripstech.apiconnector2.service.application.scan.issue.*;
import com.ripstech.apiconnector2.service.quota.QuotaAclService;
import com.ripstech.apiconnector2.service.template.GenericService;

/**
 * @deprecated replaced by {@link Api}
 */

@SuppressWarnings("unused")
@Deprecated
public class ApiConnector {

	private String baseUri;
	private String accessToken;
	private HttpClientConfig httpClientConfig;

	private ApiConnector(Builder builder) {
		this.baseUri = builder.baseUri;
		this.accessToken = builder.accessToken;
		this.httpClientConfig = builder.httpClientConfig;
	}

	public static final class Builder {

		private final String baseUri;
		private HttpClientConfig httpClientConfig;
		private HeaderAuthenticator authenticator;
		private String accessToken;
		private String username;
		private String password;
		private String clientIdName;

		public Builder(String baseUri) {
			this.baseUri = baseUri;
		}

		public Builder(String baseUri, HeaderAuthenticator authenticator) {
			this(baseUri);
			this.authenticator = authenticator;
		}

		public Builder withOAuthv2(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public Builder withOAuthv2(String username, String password) {
			this.username = username;
			this.password = password;
			return this;
		}

		public Builder withOAuthv2(String username, String password, String clientIdName) {
			withOAuthv2(username, password);
			this.clientIdName = clientIdName;
			return this;
		}

		public Builder withXPassword(String username, String password) {
			this.authenticator = new XPassword(username, password);
			return this;
		}

		public Builder withHttpClientConfig(HttpClientConfig httpClientConfig) {
			this.httpClientConfig = httpClientConfig;
			return this;
		}

		public ApiConnector build() throws ApiException {
			if (authenticator == null) {
				if(accessToken != null) {
					authenticator = new OAuth2(accessToken);
					accessToken = ((OAuth2) authenticator).getAccessToken();
				} else if(username != null && password != null) {
					if (clientIdName == null) {
						authenticator = new OAuth2(baseUri, username, password, httpClientConfig);
					} else {
						authenticator = new OAuth2(baseUri, username, password, clientIdName, httpClientConfig);
					}
					accessToken = ((OAuth2) authenticator).getAccessToken();
				} else {
					accessToken = "";
				}
			}
			if(httpClientConfig == null) {
				httpClientConfig = new HttpClientConfig();
			}
			this.httpClientConfig.setAuthenticator(authenticator);
			return new ApiConnector(this);
		}

	}

	public String getBaseUrl() {
		return this.baseUri;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	private void setPrefs(GenericService service) {
		service.setHttpClientConfig(httpClientConfig);
	}

	@SuppressWarnings("unused")
	public StatusService status() {
		StatusService service = new StatusService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public com.ripstech.apiconnector2.service.SourceService sources() {
		com.ripstech.apiconnector2.service.SourceService service = new com.ripstech.apiconnector2.service.SourceService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public LicenseService licenses() {
		LicenseService service = new LicenseService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public LogService logs() {
		LogService service = new LogService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ActivityService activities() {
		ActivityService service = new ActivityService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SettingService settings() {
		SettingService service = new SettingService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public OrganisationService organisations() {
		OrganisationService service = new OrganisationService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public QuotaService quotas() {
		QuotaService service = new QuotaService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public QuotaAclService quotaAcls(int quotaId) {
		QuotaAclService service = new QuotaAclService(baseUri, quotaId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public TeamService teams() {
		TeamService service = new TeamService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public UserService users() {
		UserService service = new UserService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public UserTokenService users(int userId) {
		UserTokenService service = new UserTokenService(baseUri, userId);
		setPrefs(service);
		return service;
	}

	public ApplicationService applications() {
		ApplicationService service = new ApplicationService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public AclService applicationAcls(int applicationId) {
		AclService service = new AclService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public AclOwnService applicationAclsOwn() {
		AclOwnService service = new AclOwnService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public UploadService applicationUploads(int applicationId) {
		UploadService service = new UploadService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

	public ScanService applicationScans(int applicationId) {
		ScanService service = new ScanService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ComparisonService applicationScanComparison(int applicationId, int scanId) {
		ComparisonService service = new ComparisonService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public StatService applicationScanStats() {
		StatService service = new StatService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public AllService applicationScanAll() {
		AllService service = new AllService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ClassService applicationScanClasses(int applicationId, int scanId) {
		ClassService service = new ClassService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public FunctionService applicationScanFunctions(int applicationId, int scanId) {
		FunctionService service = new FunctionService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ConcatService applicationScanConcats(int applicationId, int scanId) {
		ConcatService service = new ConcatService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public FileService applicationScanFiles(int applicationId, int scanId) {
		FileService service = new FileService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ProcessService applicationScanProcesses(int applicationId, int scanId) {
		ProcessService service = new ProcessService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SinkService applicationScanSinks(int applicationId, int scanId) {
		SinkService service = new SinkService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SourceService applicationScanSources(int applicationId, int scanId) {
		SourceService service = new SourceService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public IssueService applicationScanIssues(int applicationId, int scanId) {
		IssueService service = new IssueService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public CommentService applicationScanIssueComments(int applicationId, int scanId, int issueId) {
		CommentService service = new CommentService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public CommentAllService applicationScanIssueCommentAll() {
		CommentAllService service = new CommentAllService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public MarkupService applicationScanIssueMarkups(int applicationId, int scanId, int issueId) {
		MarkupService service = new MarkupService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ReviewService applicationScanIssueReviews(int applicationId, int scanId, int issueId) {
		ReviewService service = new ReviewService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SummaryService applicationScanIssueSummaries(int applicationId, int scanId, int issueId) {
		SummaryService service = new SummaryService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public TypeService applicationScanIssueTypes() {
		TypeService service = new TypeService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public OriginTypeService applicationScanIssueOriginTypes() {
		OriginTypeService service = new OriginTypeService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ReviewTypeService applicationScanIssueReviewTypes() {
		ReviewTypeService service = new ReviewTypeService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ExportService applicationScanExports(int applicationId, int scanId) {
		ExportService service = new ExportService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public CustomService applicationCustoms(int applicationId) {
		CustomService service = new CustomService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public IgnoreService applicationCustomIgnores(int applicationId, int customId) {
		IgnoreService service = new IgnoreService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SanitiserService applicationCustomSanitisers(int applicationId, int customId) {
		SanitiserService service = new SanitiserService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public com.ripstech.apiconnector2.service.application.custom.SinkService applicationCustomSinks(int applicationId, int customId) {
		com.ripstech.apiconnector2.service.application.custom.SinkService service = new com.ripstech.apiconnector2.service.application.custom.SinkService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public com.ripstech.apiconnector2.service.application.custom.SourceService applicationCustomSources(int applicationId, int customId) {
		com.ripstech.apiconnector2.service.application.custom.SourceService service = new com.ripstech.apiconnector2.service.application.custom.SourceService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ValidatorService applicationCustomValidators(int applicationId, int customId) {
		ValidatorService service = new ValidatorService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public OAuthv2Service oAuthv2() {
		OAuthv2Service service = new OAuthv2Service(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public OAuthv2ClientService oAuthv2Clients() {
		OAuthv2ClientService service = new OAuthv2ClientService(baseUri);
		setPrefs(service);
		return service;
	}

}
