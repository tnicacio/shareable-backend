package com.tnicacio.shareable.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
//	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };
//	
//	private static final String[] CLIENT = { "/users/**", "/sessions/**", "/knowledges/**" };
//
//	private static final String[] ADMIN = { "/roles/**" };
	
	
	private Environment env;
	private JwtTokenStore tokenStore;
	
	@Autowired
	public ResourceServerConfig(Environment env, JwtTokenStore tokenStore) {
		this.env = env;
		this.tokenStore = tokenStore;
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// H2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
//		.antMatchers(PUBLIC).permitAll()
//		.antMatchers(HttpMethod.GET, PUBLIC_GET).permitAll()
//		.antMatchers(CLIENT).hasAnyRole("CLIENT")
//		.anyRequest().hasRole("ADMIN");
		.anyRequest().permitAll();
	}

}