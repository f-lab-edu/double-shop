package com.project.doubleshop.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	private final SimpleTokenConfigurer simpleTokenConfigurer;

	private final SimpleAccessDeniedHandler accessDeniedHandler;

	private final EntryPointUnauthorizedHandler unauthorizedHandler;

	@Bean
	public SimpleAuthenticationTokenFilter simpleAuthenticationTokenFilter() {
		return new SimpleAuthenticationTokenFilter(simpleTokenConfigurer.getHeader(), simpleTokenConfigurer.getExpirySeconds());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(simpleAuthenticationProvider());
	}

	@Bean
	public SimpleAuthenticationProvider simpleAuthenticationProvider() {
		return new SimpleAuthenticationProvider();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private UriBasedVoter uriBasedVoter() {
		String regex = "^/api/member/(\\d+)/.*$";
		Pattern pattern = Pattern.compile(regex);
		RequestMatcher requiresAuthorizationRequestMatcher = new RegexRequestMatcher(pattern.pattern(), null);
		return new UriBasedVoter(requiresAuthorizationRequestMatcher, (String url) -> {
			Matcher matcher = pattern.matcher(url);
			return matcher.find() ? Long.parseLong(matcher.group(1)) : -1;
		});
	}

	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
		decisionVoters.add(new WebExpressionVoter());
		decisionVoters.add(uriBasedVoter());
		// 모든 voter가 승인해야 한다.
		return new UnanimousBased(decisionVoters);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.headers()
				.disable()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
			.authorizeRequests()
				.antMatchers("/api/auth").permitAll()
				.antMatchers("/api/member/join").permitAll()
				.antMatchers("/api/member/exists").permitAll()
				.antMatchers("/api/member/**").hasRole(Role.USER.name())
				.accessDecisionManager(accessDecisionManager())
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.disable();
		http
			.addFilterBefore(simpleAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
