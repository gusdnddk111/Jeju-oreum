package com.JejuOreum.config.security;

import com.JejuOreum.config.exceptionHandler.JwtExceptionFilter;
import com.JejuOreum.config.jwt.JwtAuthenticationFilter;
import com.JejuOreum.config.jwt.JwtTokenProvider;
import com.JejuOreum.constant.AccessAuthority;
import com.JejuOreum.model.service.MemberSsnMgmtDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private MemberSsnMgmtDbService memberSsnMgmtDbService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(MemberSsnMgmtDbService memberSsnMgmtDbService, JwtTokenProvider jwtTokenProvider){
        this.memberSsnMgmtDbService = memberSsnMgmtDbService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers(antMatcher("/static/**")).permitAll()
                .requestMatchers(antMatcher("/login/**")).permitAll()
                .requestMatchers(antMatcher("/**")).permitAll()
                .requestMatchers(antMatcher("/")).hasAnyAuthority(AccessAuthority.USER.getAuthorityCode(), AccessAuthority.ADMIN.getAuthorityCode())
                .anyRequest().authenticated()
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}