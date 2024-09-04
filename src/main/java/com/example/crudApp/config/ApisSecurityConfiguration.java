package com.example.crudApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class ApisSecurityConfiguration{
    @Bean
   public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin= User.withUsername("Prasanna")
                .password(encoder.encode("password1"))
                .roles("ADMIN")
                .build();
        UserDetails user= User.withUsername("Lakshmi")
                .password(encoder.encode("password2"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(antMatcher("/jobsheet/employees"),antMatcher("/")).permitAll().
                            requestMatchers(antMatcher("/jobsheet/**")).authenticated();
                }).build();


    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}