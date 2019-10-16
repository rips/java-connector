package com.ripstech.api.connector.config;

import java.net.ProxySelector;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import javax.net.ssl.*;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;


public class HttpClientConfig {

	private OkHttpClient.Builder clientBuilder;
	private OkHttpClient clientCache = null;
	private boolean isAuthenticatorSetted = false;

	public HttpClientConfig() {
		clientBuilder = new OkHttpClient.Builder()
				                .readTimeout(5, TimeUnit.MINUTES);
	}

	public HttpClientConfig setProxy(Proxy proxy) {
		clientBuilder.proxy(proxy);
		clientCache = null;
		return this;
	}

	public HttpClientConfig setProxy(Proxy proxy, String username, String password) {
		clientBuilder.proxyAuthenticator((route, response) -> {
			if(response.request().header("Proxy-Authorization") != null) {
				return null;
			}
			return response.request()
					       .newBuilder()
					       .header("Proxy-Authorization",
					               Credentials.basic(username, password))
					       .build();
		});
		clientCache = null;
		return setProxy(proxy);
	}

	public HttpClientConfig setSslSocketFactory(SSLSocketFactory sslSocketFactory, X509TrustManager x509TrustManager) {
		clientBuilder.sslSocketFactory(sslSocketFactory, x509TrustManager);
		clientCache = null;
		return this;
	}

	public HttpClientConfig setHostnameVerifier(HostnameVerifier hostnameVerifier) {
		clientBuilder.hostnameVerifier(hostnameVerifier);
		clientCache = null;
		return this;
	}

	public HttpClientConfig acceptAllCerts() throws NoSuchAlgorithmException, KeyManagementException {
		X509TrustManager x509TrustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {}

			@Override
			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
		setSslSocketFactory(sslContext.getSocketFactory(), x509TrustManager);
		setHostnameVerifier((s, sslSession) -> true);
		clientCache = null;
		return this;
	}

	public HttpClientConfig setUserAgent(String userAgent) {
		clientBuilder.addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
				                                                    .addHeader("User-Agent", userAgent)
				                                                    .build()
		                                                   ));
		clientCache = null;
		return this;
	}

	public HttpClientConfig setAuthenticator(Authenticator authenticator) {
		if(!isAuthenticatorSetted && authenticator != null) {
			isAuthenticatorSetted = true;
			clientBuilder.authenticator(authenticator);
		}
		clientCache = null;
		return this;
	}

	public HttpClientConfig addInterceptor(Interceptor interceptor) {
		clientBuilder.addInterceptor(interceptor);
		clientCache = null;
		return this;
	}

	public HttpClientConfig readTimeout(long timeout, TimeUnit unit) {
		clientBuilder.readTimeout(timeout, unit);
		clientCache = null;
		return this;
	}

	public OkHttpClient getClient() {
		if(clientCache == null) {
			clientCache = clientBuilder.build();
		}
		return clientCache;
	}

	public HttpClientConfig proxySelector(ProxySelector proxySelector) {
		clientBuilder.proxySelector(proxySelector);
		clientCache = null;
		return this;
	}

	public HttpClientConfig proxySelector(ProxySelector proxySelector, String user, String password) {
		proxyAuthenticator((route, response) -> {
			if (response.request().header("Proxy-Authorization") != null) {
				return null;
			}
			return response.request()
					.newBuilder()
					.header("Proxy-Authorization",
							Credentials.basic(user, password))
					.build();
		});
		clientCache = null;
		return proxySelector(proxySelector);
	}

	public HttpClientConfig proxyAuthenticator(Authenticator proxyAuthenticator) {
		clientBuilder.proxyAuthenticator(proxyAuthenticator);
		clientCache = null;
		return this;
	}

	public HttpClientConfig authenticator(Authenticator authenticator) {
		clientBuilder.authenticator(authenticator);
		clientCache = null;
		return this;
	}

}
