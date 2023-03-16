package ru.itis.kpfu.selyantsev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/home").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_CLIENT")
                // add pages for admin (can see all DB and can delete/add users)
                .and()
                .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/home")
                .and()
                .logout() // стирается сессия spring security
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        return httpSecurity.build();
    }
}
