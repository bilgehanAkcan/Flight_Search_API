package com.example.demo.flightSearch;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/airports/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/flights/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/airports/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/v1/flights/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/airports/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/flights/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/v1/airports/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/v1/flights/**").hasRole("USER")
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password")
                .roles("USER");
    }
}
