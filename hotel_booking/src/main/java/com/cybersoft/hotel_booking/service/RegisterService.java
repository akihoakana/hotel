package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.payload.request.SignInRequest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface RegisterService {
    UsersEntity registerNewUserAccount(SignInRequest logInRequest, String siteURL) throws UnsupportedEncodingException, MessagingException;
    boolean emailExists(String email);
    void sendVerificationEmail(String email, String siteURL) throws UnsupportedEncodingException, MessagingException;
    public void signInPassword(String email,String password);
    UsersEntity confirmByEmail(String email);
}
