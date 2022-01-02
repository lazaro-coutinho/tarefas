package br.com.lazaro.tarefas.securiry;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.lazaro.tarefas.service.UserDetailsSercice;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsSercice userDetailsSercice;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(getJWTAuthenticatonFilter())
				.addFilter(new JWTValidationFilter(authenticationManager()))
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	public JWTAuthenticationFilter getJWTAuthenticatonFilter() throws Exception {
		final JWTAuthenticationFilter filter = 
				new JWTAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/api/login");
		return filter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsSercice)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

}