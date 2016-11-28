package br.com.alaraujo.musicdashboard.config;

<<<<<<< HEAD:src/main/java/br/com/alaraujo/musicdashboard/config/SpringContextConfiguration.java
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> 329e4109169b921296a622244cf980108965f7ed:musicdashboard/src/main/java/br/com/alaraujo/musicdashboard/config/SpringContextConfiguration.java
import org.apache.commons.lang.StringUtils;
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
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtist;

@Configuration
public class SpringContextConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private ProxyProvider proxyProvider;

//	@Bean
//	public InternalResourceViewResolver internalResourceViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/jsp/");
//		resolver.setSuffix(".jsp");
//		
//		resolver.setExposedContextBeanNames("artistSearchResult");
//		
//		return resolver;
//	}

	@Bean
    protected RestTemplate restTemplate() throws Exception {

    	HttpClientBuilder clientBuilder = HttpClientBuilder.create();

    	if ( proxyProvider.isProxyEnaled() ) {
    		HttpHost myProxy = new HttpHost(proxyProvider.getProxyHost(), proxyProvider.getProxyPort());

    		if ( StringUtils.isNotBlank(proxyProvider.getProxyUser()) && StringUtils.isNotBlank(proxyProvider.getProxyPassword()) ) {				
    			CredentialsProvider credsProvider = new BasicCredentialsProvider();
    			credsProvider.setCredentials( 
    					new AuthScope(proxyProvider.getProxyHost(), proxyProvider.getProxyPort()), 
    					new UsernamePasswordCredentials(proxyProvider.getProxyUser(), proxyProvider.getProxyPassword()));
    			
    			clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();
			} else {
				clientBuilder.setProxy(myProxy).disableCookieManagement();
			}

		} else {
			clientBuilder.disableCookieManagement();
		}


        HttpClient httpClient = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION)
	public List<SpotifyArtist> artistSearchResult(){
		return new ArrayList<SpotifyArtist>();
	}
}
