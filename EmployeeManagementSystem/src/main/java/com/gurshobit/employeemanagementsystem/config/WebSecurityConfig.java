package com.gurshobit.employeemanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    private static final String[] WHITELISTED = {
            "/",
            "/h2-console/**",
            "/403",
            "/404",
            "/errors",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(WHITELISTED).permitAll()
                .antMatchers(HttpMethod.GET,"/api/employees/list","/api/employees/sort","/api/employees/search/**","/api/employees/employee/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/api/employees/add").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/employees/update/**","/api/account/roles/assign").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/employees/delete/**","/api/account/roles/assign").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/api/account/users/assign","/api/account/users/add","/api/account/roles/add").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/users","/roles").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/api/account/users","/api/account/roles").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/api/employees/list",true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .httpBasic();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling().accessDeniedPage("/403");

        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

}
