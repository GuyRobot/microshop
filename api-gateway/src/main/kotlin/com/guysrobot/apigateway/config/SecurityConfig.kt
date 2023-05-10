package com.guysrobot.apigateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests { requests ->
            requests
                .requestMatchers("/eureka/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        }
            .csrf().disable()
            .oauth2ResourceServer()
            .jwt()

        return httpSecurity.build()
    }
}