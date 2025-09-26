package com.example.domibe.global.security.config;

import com.example.domibe.global.security.jwt.JwtTokenFilter;
import com.example.domibe.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtTokenFilter jwtTokenFilter;
  private final AccessDeniedHandler accessDeniedHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(CsrfConfigurer::disable)
        .logout(LogoutConfigurer::disable)
        .formLogin(FormLoginConfigurer::disable)
        .httpBasic(HttpBasicConfigurer::disable)

        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(sessionManagement ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(exception -> exception
        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        .accessDeniedHandler(accessDeniedHandler)
    )
        .cors(cors->{})


        //경로별 권한설정
        .authorizeHttpRequests(authorize->authorize
            .requestMatchers(HttpMethod.POST,"/notice-boards").hasRole("TEACHER")
            .requestMatchers(HttpMethod.PUT,"/notice-boards").hasRole("TEACHER")
            .requestMatchers(HttpMethod.DELETE,"/notice-boards").hasRole("TEACHER")
            .requestMatchers(HttpMethod.POST,"/calendars").hasRole("TEACHER")
            .requestMatchers(HttpMethod.DELETE,"/calendars").hasRole("TEACHER")
            .requestMatchers("/auths/**").permitAll()
            .anyRequest().authenticated())

        .build();
  }
}
