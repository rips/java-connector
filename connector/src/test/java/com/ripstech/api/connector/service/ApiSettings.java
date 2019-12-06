package com.ripstech.api.connector.service;

import com.ripstech.api.connector.config.HttpClientConfig;
import com.ripstech.api.connector.AssumePropertyFile;
import com.ripstech.api.connector.authorization.OAuth2;
import com.ripstech.api.connector.exception.ApiException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@AssumePropertyFile
public abstract class ApiSettings {

	protected static HttpClientConfig HTTP_CLIENT_CONFIG;
	protected static String BASE_URL;
	protected static String USERNAME;
	protected static String PASSWORD;

	public ApiSettings() throws IOException, ApiException {
		InputStream inputStream = ApiSettings.class.getClassLoader().getResourceAsStream("config.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		BASE_URL = properties.getProperty("api.baseuri");
		USERNAME = properties.getProperty("api.username");
		PASSWORD = properties.getProperty("api.password");

		HTTP_CLIENT_CONFIG
				= new HttpClientConfig()
						  //.setAuthenticator(new XPassword(USERNAME, PASSWORD))
						  //.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8181)))
						  //.acceptAllCerts()
						  .readTimeout(1, TimeUnit.MINUTES);
		HTTP_CLIENT_CONFIG.setAuthenticator(new OAuth2(BASE_URL, USERNAME, PASSWORD, HTTP_CLIENT_CONFIG));
		//Configuration.strict = true;
	}
}
