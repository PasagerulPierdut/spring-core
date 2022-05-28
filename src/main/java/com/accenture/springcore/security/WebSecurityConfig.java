package com.accenture.springcore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/transactions/").authenticated()
                .antMatchers(HttpMethod.POST, "/transactions").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/transactions").hasRole("ADMIN")

                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password , 'true' FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.name " +
                        "FROM users u " +
                        "INNER JOIN user_roles ur ON ur.user_id=u.id " +
                        "INNER JOIN roles r ON r.id = ur.role_id " +
                        "WHERE u.username=?")
                .passwordEncoder(encoder);
    }
}
