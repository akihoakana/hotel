package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.payload.request.RegisterRequest;
import com.cybersoft.hotel_booking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterPage {
    @Autowired
    private RegisterService registerService;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @PostMapping("")
    public ResponseEntity<?> register(@RequestParam(name = "email") String email, HttpServletRequest request, HttpServletResponse response, Errors errors) throws IOException, MessagingException {
        if (registerService.emailExists(email) != null) {
            List<UsersEntity> list = registerService.emailExists(email);
            if (StringUtils.hasText(list.get(0).getTokenGoogle())) {
                System.out.println("list.get(0).getTokenGoogle() = " + list.get(0).getTokenGoogle());
                registerService.sendVerificationEmail(email,getSiteURL(request));
                response.sendRedirect(request.getContextPath()+"/register/google");
            }
            else response.sendRedirect(request.getContextPath()+"/register/password");

        }
        else
            response.sendRedirect(request.getContextPath()+"/register/insert");
        return ResponseEntity.ok(registerService.emailExists(email));
    }
    @PostMapping("/google")
    public ResponseEntity<?> signInGoogle(@Valid @RequestBody RegisterRequest registerRequest, HttpServletRequest request, Errors errors) throws UnsupportedEncodingException, MessagingException {
        return ResponseEntity.ok("Đã gửi email để đăng nhập bằng google");

    }
    @PostMapping("/password")
    public ResponseEntity<?> signInPassword(@Valid @RequestBody RegisterRequest registerRequest, HttpServletRequest request, Errors errors) throws UnsupportedEncodingException, MessagingException {
        registerService.signInPassword(registerRequest.getEmail(),registerRequest.getPassword());
        return ResponseEntity.ok("Page đổi pass");
    }
    @PostMapping("/resend")
    public ResponseEntity<?> resendconfirmByEmail(@RequestParam(name = "email") String email, HttpServletRequest request, Errors errors) throws UnsupportedEncodingException, MessagingException {
        registerService.sendVerificationEmail(email,getSiteURL(request));
        return ResponseEntity.ok("Đã gửi mail");
    }

    @PostMapping("/verify/{email}")
    public ResponseEntity<?> confirmByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(registerService.confirmByEmail(email));
    }


    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
