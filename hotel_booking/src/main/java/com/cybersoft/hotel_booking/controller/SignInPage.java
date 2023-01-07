package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@RestController
@RequestMapping("/signin")
public class SignInPage {
//    @Autowired
//    private OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    private SigninService signinService;
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(Principal principal) {
        System.out.println("principal = " + principal);
        return ResponseEntity.ok(principal);
    }
//    @GetMapping("/signinGoogle")
//    public ResponseEntity<?> getLoginInfo(Model model, OAuth2AuthenticationToken authentication, Principal principal) {
//        OAuth2AuthorizedClient client = authorizedClientService
//                .loadAuthorizedClient(
//                        authentication.getAuthorizedClientRegistrationId(),
//                        authentication.getName());
//        System.out.println("client.getClientRegistration() = " + client.getClientRegistration().toString());
//        return ResponseEntity.ok(client) ;
//    }
//    @GetMapping("/client")
//    public ResponseEntity<?> client(Model model, OAuth2AuthenticationToken authentication, Principal principal) {
//        OAuth2AuthorizedClient client = authorizedClientService
//                .loadAuthorizedClient(
//                        authentication.getAuthorizedClientRegistrationId(),
//                        authentication.getName());
//        return ResponseEntity.ok(client) ;
//    }
    @PostMapping("/newpassword")
    public ResponseEntity<?> newPassord(@RequestParam(name = "password") String password,@RequestParam(name = "passwordConfirm") String passwordConfirm,@RequestParam(name = "password") String email) {
        signinService.newPassord(email, password, passwordConfirm);

        return ResponseEntity.ok("Update password thành côngg");
    }
    @GetMapping("/forgetpassword")
    public ResponseEntity<?> forgetPassord(@RequestParam String mail, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        signinService.sendForgetPass(mail,getSiteURL(request));
        return ResponseEntity.ok("Đã gửi mail");

    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
