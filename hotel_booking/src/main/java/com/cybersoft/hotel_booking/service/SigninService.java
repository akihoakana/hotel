package com.cybersoft.hotel_booking.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface SigninService {

    //    List<RoleEntity> getRoles(String email);
    void sendForgetPass(String email, String siteURL) throws UnsupportedEncodingException, MessagingException;

    boolean newPassord(String email, String password,String passwordConfirm);

}
