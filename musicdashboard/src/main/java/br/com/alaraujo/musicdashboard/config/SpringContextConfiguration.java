package br.com.alaraujo.musicdashboard.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringContextConfiguration {

	@Autowired
	private ProxyProvider proxyProvider;

	@Bean
    protected RestTemplate restTemplate() throws Exception {

    	HttpClientBuilder clientBuilder = HttpClientBuilder.create();

    	if (proxyProvider.isProxyEnaled()) {			
    		CredentialsProvider credsProvider = new BasicCredentialsProvider();
    		credsProvider.setCredentials( 
    				new AuthScope(proxyProvider.getProxyHost(), proxyProvider.getProxyPort()), 
    				new UsernamePasswordCredentials(proxyProvider.getProxyUser(), proxyProvider.getProxyPassword()));
    		
    		HttpHost myProxy = new HttpHost(proxyProvider.getProxyHost(), proxyProvider.getProxyPort());
    		clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();
		} else {
			clientBuilder.disableCookieManagement();
		}


        HttpClient httpClient = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }
}
