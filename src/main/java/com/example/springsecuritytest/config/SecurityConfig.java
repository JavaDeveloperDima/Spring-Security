package com.example.springsecuritytest.config;

import com.example.springsecuritytest.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SuccessUserHandler successUserHandler;
    @Autowired
    private UserServiceImpl userServiceClass;
    @Autowired
    private Encoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/api/**").hasAnyRole("ADMIN")
                .antMatchers("/new").permitAll()
                .and()
                .formLogin().successHandler(successUserHandler)
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(encoder.getPasswordEncoder());
        authenticationProvider.setUserDetailsService(userServiceClass);
        return authenticationProvider;
    }
}
