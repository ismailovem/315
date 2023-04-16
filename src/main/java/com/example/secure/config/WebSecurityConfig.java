package com.example.secure.config;

import com.example.secure.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests() //выше удалить
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .antMatchers("/api/users").hasRole("ADMIN")
                .antMatchers("/api/admin").hasRole("ADMIN")
                .antMatchers("/api/user").hasAnyRole("USER","ADMIN")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PATCH, "/api/**").hasRole("ADMIN")
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
