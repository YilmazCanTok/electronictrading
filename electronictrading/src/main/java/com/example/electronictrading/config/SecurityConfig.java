package com.example.electronictrading.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("yilmaz")
                .password("{noop}123456") // {noop} for plain text password
                .roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF korumasını devre dışı bırak
                .authorizeRequests()
                .antMatchers("/api/cart").permitAll()
                .antMatchers("/api/customer").permitAll()
                .antMatchers("/api/order").permitAll()
                .antMatchers("/api/product").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
