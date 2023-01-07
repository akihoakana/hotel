package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.payload.request.RegisterRequest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface RegisterService {
    UsersEntity checkLogin(String email);
    UsersEntity registerNewUserAccount(RegisterRequest registerRequest, String siteURL) throws UnsupportedEncodingException, MessagingException;
    List<UsersEntity> emailExists(String email);
    void sendVerificationEmail(String email, String siteURL) throws UnsupportedEncodingException, MessagingException;
    public void signInPassword(String email,String password);
    UsersEntity confirmByEmail(String email);
}
