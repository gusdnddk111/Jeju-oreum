package com.JejuOreum.config.security;

import com.JejuOreum.constant.AccessAuthority;
import com.JejuOreum.model.entity.MemberSsnMgmtEntity;
import com.JejuOreum.model.service.MemberSsnMgmtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Component
public class SessionCheckFilter extends OncePerRequestFilter {

    private final MemberSsnMgmtService memberSsnMgmtService;

    public SessionCheckFilter(MemberSsnMgmtService memberSsnMgmtService){
        this.memberSsnMgmtService = memberSsnMgmtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String sessionKey = request.getHeader("SESSION_KEY");
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Optional<MemberSsnMgmtEntity> memberSsnMgmtEntity = memberSsnMgmtService.findBySessionKey(sessionKey);

        if(sessionKey == null || memberSsnMgmtEntity.isEmpty()){
            Collection<SimpleGrantedAuthority> authorityCollections = new ArrayList<>();
            authorityCollections.add(new SimpleGrantedAuthority(AccessAuthority.GUEST.toString()));
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(sessionKey,null, authorityCollections);

            context.setAuthentication(authenticationToken);

            filterChain.doFilter(request,response);
            return;
        }

        String accessAuthority = memberSsnMgmtEntity.get().getAccessAuthority().toString();
        Collection<SimpleGrantedAuthority> authorityCollections = new ArrayList<>();
        authorityCollections.add(new SimpleGrantedAuthority(accessAuthority));

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(sessionKey,null,authorityCollections);

        context.setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}
