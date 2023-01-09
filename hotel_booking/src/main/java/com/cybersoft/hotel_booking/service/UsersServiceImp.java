package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.TokenExpiredEntity;
import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.repository.TokenRepository;
import com.cybersoft.hotel_booking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UsersServiceImp implements UsersService{
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public UsersEntity checkLogin(String email) {
        return usersRepository.findByEmail(email).size() > 0 ? usersRepository.findByEmail(email).get(0) : null;
    }

    @Override
    public List<TokenExpiredEntity> invalidToken(String token) {
        TokenExpiredEntity tokenExpiredEntity =new TokenExpiredEntity();
        tokenExpiredEntity.setName(token);
        tokenRepository.save(tokenExpiredEntity);
        return tokenRepository.findAll();
    }

    @Override
    public boolean checkToken(String token) {
        String abc =null;
        System.out.println("tokenRepository.findByName(token) = " + tokenRepository.findByName(token));
    return tokenRepository.findByName(token)!=null ;
    }

}
