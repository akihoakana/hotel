package com.cybersoft.hotel_booking.service.Imp;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.repository.UsersRepository;
import com.cybersoft.hotel_booking.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SigninServiceImp implements SigninService {
    @Autowired
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Override
    public List<UsersEntity> newPassword(String email, String password, String passwordConfirm) {
        List<UsersEntity> usersEntities= usersRepository.findByEmail(email);
        if (usersEntities.size()>0 && StringUtils.hasText(passwordConfirm) && StringUtils.hasText(password)) {
            if (password.equals(passwordConfirm)) {
                usersEntities.get(0).setPassword(passwordEncoder.encode(password));
                usersRepository.saveAll(usersEntities);
                System.out.println("newPassord thành công");
                return usersEntities;
            }
            else{System.out.println("Hai password không giống nhau");
                return null;
                }
        }
        else{
            System.out.println("newPassword thất bại");
            return null;
        }
    }


}
