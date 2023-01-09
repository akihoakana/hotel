package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.payload.request.LogInRequest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface RegisterService {
    UsersEntity registerNewUserAccount(LogInRequest logInRequest, String siteURL) throws UnsupportedEncodingException, MessagingException;
    boolean emailExists(String email);
    void sendVerificationEmail(String email, String siteURL) throws UnsupportedEncodingException, MessagingException;
    public void signInPassword(String email,String password);
    UsersEntity confirmByEmail(String email);
}
