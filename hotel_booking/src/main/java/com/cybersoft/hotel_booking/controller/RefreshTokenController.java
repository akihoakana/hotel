package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.jwt.JwtTokenHelper;
import com.cybersoft.hotel_booking.payload.response.DataResponse;
import com.cybersoft.hotel_booking.payload.response.DataTokenResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/refresh-token")
public class RefreshTokenController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    private Gson gson = new Gson();
    private final long expiredDate = 8 * 60 * 60 * 1000;
    private final long refreshExpiredDate = 80 * 60 * 60 * 1000;

    @PostMapping("")
    public ResponseEntity<?> refreshToken(@RequestParam("token") String token){
        DataResponse dataResponse = new DataResponse();
        DataTokenResponse dataTokenResponse = new DataTokenResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setDesc("");
        if(jwtTokenHelper.validaToken(token)){
            String json = jwtTokenHelper.decodeToken(token);
            Map map = gson.fromJson(json, Map.class);
            if(StringUtils.hasText(map.get("type").toString())
                    && map.get("type").toString().equals("refesh")){
                List<GrantedAuthority> roles;
                roles = Arrays.stream(map.get("roles").toString()
                        .replace("[","")
                        .replace("]","")
                        .replace("{role=","")
                        .replace("}","")
                        .split(", "))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
                String tokenAuthen = jwtTokenHelper.generateToken(map.get("username").toString(),"authen", roles,expiredDate);
                String refeshToken = jwtTokenHelper.generateToken(map.get("username").toString(),"refesh",roles,refreshExpiredDate);
                dataTokenResponse.setToken(tokenAuthen);
                dataTokenResponse.setRefreshToken(refeshToken);
                dataResponse.setData(dataTokenResponse);
            }
        }else{
            dataResponse.setData("");

        }


        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
