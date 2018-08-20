package com.ripstech.apiconnector2;

import com.ripstech.apiconnector2.authorization.HeaderAuthenticator;
import com.ripstech.apiconnector2.authorization.OAuth2;
import com.ripstech.apiconnector2.authorization.XPassword;
import com.ripstech.apiconnector2.config.HttpClientConfig;
import com.ripstech.apiconnector2.connector.path.ApplicationPath;
import com.ripstech.apiconnector2.connector.path.QuotaPath;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.*;
import com.ripstech.apiconnector2.service.template.GenericService;

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

		public Api build() throws ApiException {
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
