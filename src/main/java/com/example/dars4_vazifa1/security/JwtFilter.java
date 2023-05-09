package com.example.dars4_vazifa1.security;

import com.example.dars4_vazifa1.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    String getUsername;

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    MyAuthService myAuthService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")){
            token = token.substring(7);
            boolean validateToken = jwtProvider.validateToken(token);
            if (validateToken){
                String username = jwtProvider.getUsernameFromToken(token);
                getUsername = username;
                UserDetails userDetails = myAuthService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request,response);
    }

    public String getUsername(){
        String username1 = getUsername;
        return username1;
    }
}
