package com.gamotrance.OTT.configs;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.gamotrance.OTT.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = mongoUserDetails();
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

	}

	//RentMovieControllergetPasswordCheeck
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
////		 
	http.csrf().disable();
	
	
	
	
//	  http.httpBasic().disable().csrf().disable().sessionManagement()
//	  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	  .and().authorizeRequests() .antMatchers("/User/login").permitAll()
//	  .antMatchers("/User/register").permitAll()
//	  .antMatchers("/User/updateUser").permitAll()
//	  .antMatchers("/User/getPasswordCheeck").permitAll()
//	  .antMatchers("/User/getPhoneVerify").permitAll()///updatePassword
//	  .antMatchers("/User/updatePassword").permitAll()//
//	  .antMatchers("/swagger-ui.html/**").permitAll()
//	  .antMatchers("/DashBoardController/**").hasAuthority("USER")
//	  .antMatchers("/Video/**").hasAuthority("USER")
//	  .antMatchers("/User/**").hasAuthority("USER")
//	  .antMatchers("/RentMovieController/**").hasAuthority("USER")
//	  .antMatchers("/Plan/**").hasAuthority("USER")
//	  .anyRequest().authenticated().and().csrf() .disable().exceptionHandling()
//	  .authenticationEntryPoint(unauthorizedEntryPoint()).and() .apply(new
//	  JwtConfigurer(jwtTokenProvider));
//	 
	 
	 
	 
	 
	}

	
	
//	
//	  @Override public void configure(WebSecurity web) throws Exception {
//	  web.ignoring().antMatchers("/swagger-resources/**")//
//	  .antMatchers("/swagger-ui.html/**")// .antMatchers("/configuration/**")//
//	  .antMatchers("/webjars/**")// .antMatchers("/public")
//	  .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
//	  "/images/**"); }
	 
	 
	 
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"Unauthorized");
	}

	@Bean
	public UserDetailsService mongoUserDetails() {
		return new CustomUserDetailsService();
	}
}
