package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.payload.request.SignInRequest;
import com.cybersoft.hotel_booking.model.RegisterResponseModel;
import com.cybersoft.hotel_booking.payload.response.DataResponse;
import com.cybersoft.hotel_booking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/register")

public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @PostMapping("")
    public ResponseEntity<?> register(@Valid @RequestBody SignInRequest logInRequest, HttpServletRequest request) throws IOException, MessagingException {
        String verifyURL = request.getRequestURL().toString()
                .replace(request.getServletPath(), "") + "/register/verify/"+logInRequest.getEmail();
        UsersEntity usersEntity = registerService.registerNewUserAccount(logInRequest,verifyURL);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setDesc("");
        if (usersEntity==null){
            System.out.println("There is an account with that email address: "
                    + logInRequest.getEmail());
            dataResponse.setData("There is an account with that email address: "
                    + logInRequest.getEmail());
        }
        else {
            RegisterResponseModel registerResponseModel =new RegisterResponseModel();
            registerResponseModel.setUsersEntity(usersEntity);
            registerResponseModel.setLinkActive(verifyURL);
            dataResponse.setData(registerResponseModel);
        }
        return ResponseEntity.ok(dataResponse);
    }
    @PostMapping("/resend/{email}")
    public ResponseEntity<?> resendconfirmByEmail(@PathVariable("email") String email, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setDesc("");
        String verifyURL = request.getRequestURL().toString()
                .replace(request.getServletPath(), "") + "/register/verify/"+email;
        registerService.sendVerificationEmail(email, verifyURL);
        dataResponse.setData(verifyURL);
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping("/verify/{email}")
    public ResponseEntity<?> confirmByEmail(@PathVariable("email") String email) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setDesc("");
        UsersEntity usersEntity = registerService.confirmByEmail(email);
        if (usersEntity==null){
            System.out.println("Tài khoản không tồn tại");
            dataResponse.setData("Tài khoản không tồn tại");
        }
        else
        {
//                System.out.println("Tài khoản đã được xác nhận");
//                dataResponse.setData("Tài khoản đã được xác nhận");
                dataResponse.setData(usersEntity);
        }
        return ResponseEntity.ok(dataResponse);
    }
}
