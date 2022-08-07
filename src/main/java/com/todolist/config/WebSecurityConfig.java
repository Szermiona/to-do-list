package com.todolist.config;

import com.todolist.config.userlogging.CustomAuthenticationProvider;
import com.todolist.config.userlogging.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Properties;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private CustomUserDetailsService userPrincipalDetailService;
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public void setWebSecurityConfig(CustomUserDetailsService userPrincipalDetailService, AuthenticationFailureHandler authenticationFailureHandler) {
        this.userPrincipalDetailService = userPrincipalDetailService;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        users.put("user", "{noop}user,ROLE_USER,enabled");
        users.put("admin", "{noop}admin,ROLE_ADMIN,ROLE_USER,enabled");
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/img/**", "/bootstrap/**", "/js/**", "/favicon/**").permitAll()
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/add-task", "/delete-task/**", "/delete-all").hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "/index", "/signup", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureHandler(authenticationFailureHandler).permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/login?logout").permitAll()
                .deleteCookies("JSESSIONID");
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new CustomAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
