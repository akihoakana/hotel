package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SigninServiceImp implements  SigninService{
    @Autowired
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Override
    public List<UsersEntity> newPassord(String email, String password, String passwordConfirm) {
        List<UsersEntity> usersEntities= usersRepository.findByEmail(email);
        if (usersEntities.size()>0 && StringUtils.hasText(passwordConfirm) && StringUtils.hasText(password)) {
            if (password.equals(passwordConfirm)) {
                usersEntities.get(0).setPassword(passwordEncoder.encode(password));
                System.out.println("usersEntities.get(0).newPassword() = " + usersEntities.get(0).getPassword());
                usersRepository.saveAll(usersEntities);
                System.out.println("newPassord thành công");
                return usersEntities;
            }
            else{System.out.println("Sai password cũ");
                return null;
                }
        }
        else{
            System.out.println("newPassord thất bại");
            return null;
        }
    }


}
