package com.example.boss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
    @Bean
    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    String[] staticResources = {
            "/assets/**","/fontawesome*","/webjars/**"};
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	http.csrf().disable();
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/").permitAll()
                		.requestMatchers("/register/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/users").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/pet/**").hasRole("USER")
                        .requestMatchers("/pets").hasRole("USER")
                        .requestMatchers("/dogs").permitAll()
                        .requestMatchers("/cats").permitAll()
                        .requestMatchers("/dog/**").permitAll()
                        .requestMatchers("/cat/**").permitAll()
                        .requestMatchers("/chat/**").hasRole("USER")
                        .requestMatchers("/messages/**").permitAll()
                        .requestMatchers("/stomp/**").permitAll()
                        .requestMatchers(staticResources).permitAll()
                     
        ).formLogin(
                form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
        ).logout(
                logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
        );
        http.rememberMe(me -> me.key("uniqueAndSecret").tokenValiditySeconds(30));
        return http.build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
   
}

