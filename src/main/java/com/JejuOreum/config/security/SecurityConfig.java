package com.JejuOreum.config.security;

import com.JejuOreum.model.service.MemberSsnMgmtDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private MemberSsnMgmtDbService memberSsnMgmtDbService;

    @Autowired
    public SecurityConfig(MemberSsnMgmtDbService memberSsnMgmtDbService){
        this.memberSsnMgmtDbService = memberSsnMgmtDbService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new SessionCheckFilter(memberSsnMgmtDbService), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(antMatcher("/static/**")).permitAll()
                        .requestMatchers(antMatcher("/login/**")).permitAll()
                        .requestMatchers(antMatcher("/**")).permitAll()
                        //.requestMatchers(antMatcher("/")).hasAnyAuthority(AccessAuthority.USER.getRole(), AccessAuthority.ADMIN.getRole())
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}