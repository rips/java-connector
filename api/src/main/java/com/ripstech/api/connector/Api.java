package com.ripstech.api.connector;

import com.ripstech.api.connector.authorization.HeaderAuthenticator;
import com.ripstech.api.connector.authorization.OAuth2;
import com.ripstech.api.connector.authorization.XPassword;
import com.ripstech.api.connector.config.HttpClientConfig;
import com.ripstech.api.connector.connector.path.ApplicationPath;
import com.ripstech.api.connector.connector.path.QuotaPath;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.*;
import com.ripstech.api.connector.service.template.GenericService;

@SuppressWarnings("unused")
public class Api {

	private String baseUri;
	private String accessToken;
	private HttpClientConfig httpClientConfig;

	private Api(Builder builder) {
		this.baseUri = builder.baseUri;
		this.accessToken = builder.accessToken;
		this.httpClientConfig = builder.httpClientConfig;
	}

	public static final class Builder {

		private final String baseUri;
		private HttpClientConfig httpClientConfig;
		private HeaderAuthenticator authenticator;
		private String accessToken;
		private String email;
		private String password;
		private String clientIdName;
		private String clientId;

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

		public Builder withOAuthv2(String email, String password) {
			this.email = email;
			this.password = password;
			return this;
		}

		public Builder withOAuthv2(String email, String password, String clientIdName) {
			withOAuthv2(email, password);
			this.clientIdName = clientIdName;
			return this;
		}

		public Builder withOAuthv2ClientId(String email, String password, String clientId) {
			withOAuthv2(email, password);
			this.clientId = clientId;
			return this;
		}

		public Builder withXPassword(String email, String password) {
			this.authenticator = new XPassword(email, password);
			return this;
		}

		public Builder withHttpClientConfig(HttpClientConfig httpClientConfig) {
			this.httpClientConfig = httpClientConfig;
			return this;
		}

		public Api build() throws ApiException {
			if (authenticator == null) {
				if(accessToken != null) {
					authenticator = new OAuth2(accessToken);
					accessToken = ((OAuth2) authenticator).getAccessToken();
				} else if(email != null && password != null) {
					if (clientIdName != null && clientId == null) {
						authenticator = new OAuth2(baseUri, email, password, clientIdName, httpClientConfig);
					} else if (clientId != null && clientIdName == null) {
						authenticator = new OAuth2(baseUri, email, password, clientId, httpClientConfig, true);
					} else {
						authenticator = new OAuth2(baseUri, email, password, httpClientConfig);
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
			return new Api(this);
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
	public LanguageService languages() {
		LanguageService service = new LanguageService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public CallbackService callbacks() {
		CallbackService service = new CallbackService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SourceService sources() {
		SourceService service = new SourceService(baseUri);
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
	public OrganizationService organizations() {
		OrganizationService service = new OrganizationService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public QuotaService quotas() {
		QuotaService service = new QuotaService(baseUri);
		setPrefs(service);
		return service;
	}

	public QuotaPath quota(int quotaId) {
		QuotaPath path = new QuotaPath(baseUri, quotaId);
		path.setHttpClientConfig(httpClientConfig);
		return path;
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

	public ApplicationPath application(long applicationId) {
		ApplicationPath path = new ApplicationPath(baseUri, applicationId);
		path.setHttpClientConfig(httpClientConfig);
		return path;
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
