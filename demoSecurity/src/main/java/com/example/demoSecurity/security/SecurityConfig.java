package com.example.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll() //permitAll(): cho phép truy cập tự do không vần xác thực
                .requestMatchers("/category").hasAnyRole("USER","ADMIN") // Sử dụng cho nhiều role được liệt kê
                .requestMatchers("/user").hasRole("ADMIN") // chỉ sử dụng cho 1 role
                .requestMatchers("/posts").hasAnyRole("USER","ADMIN","AUTHOR")
//                .requestMatchers("/author").hasRole("AUTHOR") // chỉ sử dụng cho 1 role
                .anyRequest().authenticated() // authenticated() băts buộc phải đăng nhập mới dùng được
                .and()
                .formLogin().defaultSuccessUrl("/",true)
                .and()
                .logout().logoutSuccessUrl("/");
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("USER","ADMIN")
                .build();
        UserDetails author = User.withDefaultPasswordEncoder()
                .username("author")
                .password("123")
                .roles("AUTHOR")
                .build();
        UserDetails sale = User.withDefaultPasswordEncoder()
                .username("sale")
                .password("123")
                .roles("SALE")
                .build();
        return new InMemoryUserDetailsManager(user,admin,author,sale);
    }
}
