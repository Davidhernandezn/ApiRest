package com.gs.training.petardocore.auth;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <b>WebSecurityConfig.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: WebSecurityConfig Class
 * @author: CoE Microservicios
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends OncePerRequestFilter {

	/**
	 * Class constants for validations and parameters
	 */

	/* Constant ALLOED_HEADERS of type String */
	private static final String ALLOED_HEADERS = "*";
	/* Constant GET of type String */
	private static final String GET = "GET";
	/* Constant POST of type String */
	private static final String POST = "POST";
	/* Constant PUT of type String */
	private static final String PUT = "PUT";
	/* Constant PATTER_CONFIG of type String */
	private static final String PATTER_CONFIG = "/**";
	/* Constant BASE_PATH of type String */
	@Value("${basePath}")
	private String basePath;
	/* Constant urlActuador of type String */
	@Value("${management.endpoints.web.base-path}")
	private String urlActuator;

	public WebSecurityConfig() {
		/**
		 * Default constructor
		 */
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.addHeader("Expect-CT", "max-age=3600, enforce");
		filterChain.doFilter(request, response);
	}

	/**
	 * Configuration of the access cors
	 *
	 * @return CORS configurations
	 */
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		// Allow all headers
		configuration.setAllowedOriginPatterns(Arrays.asList(ALLOED_HEADERS));
		// Allow POST and GET methods
		configuration.setAllowedMethods(Arrays.asList(GET, POST, PUT));
		configuration.setAllowedHeaders(Arrays.asList(ALLOED_HEADERS));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(PATTER_CONFIG, configuration);
		return source;
	}

	/**
	 * request configuration
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.headers(h -> h.contentSecurityPolicy(c -> c.policyDirectives("default-src 'self'")));
		http.headers(h -> h.addHeaderWriter(new StaticHeadersWriter("Expect-CT", "max-age=3600, enforce")));
		http.csrf(csrf -> csrf.requireCsrfProtectionMatcher(new OrRequestMatcher(new AntPathRequestMatcher(basePath))));
		http.authorizeHttpRequests(auth -> auth.requestMatchers(basePath).permitAll().requestMatchers(basePath)
				.permitAll().anyRequest().permitAll());
		http.cors(Customizer.withDefaults());

		return http.build();
	}

	/**
	 * WebSecurityCustomizer
	 *
	 * @return WebSecurityCustomizer
	 */
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {

		return web -> web.ignoring().requestMatchers(HttpMethod.GET, urlActuator);
	}
}
