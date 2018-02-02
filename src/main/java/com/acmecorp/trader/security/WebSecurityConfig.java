package com.acmecorp.trader.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .httpBasic()
//                .and()
//                .csrf().disable()
//                .httpBasic()
//                .and()
//                .headers().frameOptions().disable();

        http.authorizeRequests().antMatchers("/orders").hasRole("ADMIN")
            .antMatchers("/order/**", "/orders/trader/**").hasAnyRole("TRADER")
                .anyRequest().permitAll()
                .and()
            .httpBasic()
                .and()
                .csrf().disable()
            .httpBasic()
                .and()
                .headers().frameOptions().disable();


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("trader1").password("password").roles("TRADER").and()
                .withUser("trader2").password("password").roles("TRADER").and()
                .withUser("admin").password("password").roles("ADMIN")
                ;
    }
}
