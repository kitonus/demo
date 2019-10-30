package com.jatis.tripatra.demo.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().httpBasic().and()
        	.formLogin().disable()
            .authorizeRequests()
            .antMatchers("/swagger-ui.html/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/csrf").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/v2/api-docs").permitAll()           
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/api/public").permitAll()
            .antMatchers("/api/authenticate").permitAll()
            .anyRequest().permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
