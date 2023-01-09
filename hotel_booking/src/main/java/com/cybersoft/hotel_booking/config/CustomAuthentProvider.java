package com.cybersoft.hotel_booking.config;

import com.cybersoft.hotel_booking.entity.RolesEntity;
import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.service.SigninService;
import com.cybersoft.hotel_booking.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomAuthentProvider implements AuthenticationProvider {

    @Autowired
    private SigninService signinService;
    @Autowired
    private UsersService usersService;
    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UsersEntity userEntity = usersService.checkLogin(email);

        if(userEntity != null){
            boolean isMatchPassword = passwordEncoder.matches(password,userEntity.getPassword());
            if(isMatchPassword){
                RolesEntity rolesEntity =userEntity.getRoles();
                List<GrantedAuthority> authList =new ArrayList<>();
                SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority(rolesEntity.getRoleName());
                authList.add(simpleGrantedAuthority);
                return new UsernamePasswordAuthenticationToken(userEntity.getEmail(),userEntity.getPassword(),authList);
            }else{
                return null;
            }

        }else{
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
