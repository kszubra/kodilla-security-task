package com.kodilla.task.security.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String ROLE_1 = "R1";
    private final String ROLE_2 = "R2";
    private final String ROLE_3 = "R3";

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("r1").password("r1").roles(ROLE_1)
                .and()
                .withUser("r2").password("r2").roles(ROLE_2)
                .and()
                .withUser("r3").password("r3").roles(ROLE_3);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/m1")
                .hasAnyRole(ROLE_1, ROLE_2, ROLE_3)
                .mvcMatchers(HttpMethod.GET, "/m2")
                .hasAnyRole(ROLE_3, ROLE_2)
                .mvcMatchers(HttpMethod.GET, "/m3")
                .hasAnyRole(ROLE_3)
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic();
    }
}
