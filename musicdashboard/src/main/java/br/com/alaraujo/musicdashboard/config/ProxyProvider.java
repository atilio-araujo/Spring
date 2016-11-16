package br.com.alaraujo.musicdashboard.config;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.springframework.stereotype.Component;

@Component
public class ProxyProvider {

	private boolean isProxyEnaled = false;
	private String proxyHost;
	private Integer proxyPort;
	private String proxyUser;
	private String proxyPassword;
	private CredentialsProvider credentialProvider;

	public ProxyProvider() {
		isProxyEnaled = System.getProperty("http.proxySet") != null && System.getProperty("http.proxySet").equals("true");

		if ( isProxyEnaled ) {
			proxyHost = System.getProperty("http.proxyHost");

			if (NumberUtils.isNumber(System.getProperty("http.proxyPort"))){				
				proxyPort = new Integer(System.getProperty("http.proxyPort"));
			} else {
				isProxyEnaled = false;
			}

			proxyUser = System.getProperty("http.proxyUser");
			proxyPassword = System.getProperty("http.proxyPassword");

			credentialProvider = new BasicCredentialsProvider();
			credentialProvider.setCredentials(new AuthScope(proxyHost, proxyPort, AuthScope.ANY_HOST, "ntlm"), new NTCredentials(proxyUser, proxyPassword, "", ""));
		}
	}

	public boolean isProxyEnaled() {
		return isProxyEnaled;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public String getProxyUser() {
		return proxyUser;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public CredentialsProvider getCredentialProvider() {
		return credentialProvider;
	}

}
