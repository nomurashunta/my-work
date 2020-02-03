package com.example.apiApp.domain.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * セキュリティ設定のためのクラスです。
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 設定を行います。
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/public").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .csrf().disable()
                .addFilter(new MyFilter(authenticationManager()));
    }

}
