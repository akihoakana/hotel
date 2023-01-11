package com.cybersoft.hotel_booking.jwt;



import com.cybersoft.hotel_booking.service.UsersService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    UsersService usersService;
    private Gson gson = new Gson();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromHeader(request);
        if(token != null){
            if(jwtTokenHelper.validaToken(token)){
                if (!usersService.checkToken(token)){
                    String json = jwtTokenHelper.decodeToken(token);
                    Map map = gson.fromJson(json, Map.class);
                    List<GrantedAuthority> roles =
                            Arrays.stream(map.get("roles").toString()
                            .replace("[","")
                            .replace("]","")
                            .replace("{role=","")
                            .replace("}","")
                            .split(", "))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                    if(StringUtils.hasText(map.get("type").toString())
                            && !map.get("type").toString().equals("refesh")) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(map.get("username"), "", roles);
                        System.out.println("authenticationToken = " + authenticationToken.toString());
                        System.out.println("authenticationToken.getAuthorities() = " + authenticationToken.getAuthorities());
                        SecurityContext securityContext = SecurityContextHolder.getContext();
                        securityContext.setAuthentication(authenticationToken);
                    }
                }
            }
        }
        filterChain.doFilter(request,response);
    }


    private String getTokenFromHeader(HttpServletRequest request){
        String strToken = request.getHeader("Authorization");
        if(StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")){
            String finalToken = strToken.substring(7);
            return finalToken;
        }else{
            return null;
        }
    }
}


