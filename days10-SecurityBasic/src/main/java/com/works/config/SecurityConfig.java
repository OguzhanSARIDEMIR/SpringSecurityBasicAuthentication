package com.works.config;

import com.works.services.UserServices;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserServices userServices;
    public SecurityConfig(UserServices userServices) {
        this.userServices = userServices;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServices).passwordEncoder(userServices.passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/current/**").hasRole("current")
                .antMatchers("/product/**").hasRole("product")
                .antMatchers("/user/**").permitAll()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }



}