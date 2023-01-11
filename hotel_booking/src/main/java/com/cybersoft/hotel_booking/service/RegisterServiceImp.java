package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.RolesEntity;
import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.payload.request.SignInRequest;
import com.cybersoft.hotel_booking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class RegisterServiceImp implements RegisterService {
    private final long expiredDate = 30 * 1000;
    private final long expiredDate1 = 2*60 * 1000;
    @Value("${spring.mail.username}")
    private String emailown;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesService rolesService;
    @Override
    public UsersEntity registerNewUserAccount(SignInRequest logInRequest, String siteURL) throws UnsupportedEncodingException, MessagingException {
        if (emailExists(logInRequest.getEmail())) {
            System.out.println("There is an account with that email address: "
                    + logInRequest.getEmail());
            return null;
        }
        else {
            UsersEntity UsersEntity = new UsersEntity();
            UsersEntity.setEmail(logInRequest.getEmail());
            RolesEntity rolesEntity = new RolesEntity();
            System.out.println("rolesService.getRole(logInRequest.getRole() = " + rolesService.getRole(logInRequest.getRole()));
            System.out.println("logInRequest.getRole() = " + logInRequest.getRole());
            if (!StringUtils.hasText(logInRequest.getRole()) || rolesService.getRole(logInRequest.getRole())==null)
            {
                rolesEntity =rolesService.getRole("regular");
                System.out.println("rolesEntity1 = " + rolesEntity);
            }
            else {
                rolesEntity =rolesService.getRole(logInRequest.getRole());
                System.out.println("rolesEntity = " + rolesEntity);
            }
            rolesEntity.setRoleName(rolesEntity.getRoleName());
            UsersEntity.setRoles(rolesEntity);
            UsersEntity.setPassword(passwordEncoder.encode(logInRequest.getPassword()));
            System.out.println("Đăng kí thành công");
            sendVerificationEmail(logInRequest.getEmail(), siteURL);
            return usersRepository.save(UsersEntity);
        }
    }
    @Override
    public UsersEntity confirmByEmail(String email) {
        List<UsersEntity> usersEntities= usersRepository.findByEmail(email);
        if (usersEntities.size()>0){
            usersEntities.get(0).setEmailVerify(true);
            usersRepository.save(usersEntities.get(0));
            return usersRepository.save(usersEntities.get(0));
        }
        else
            return null;
    }


    @Override
    public boolean emailExists(String email) {
        List<UsersEntity> usersEntities= usersRepository.findByEmail(email);
        return  (usersEntities.size()>0)?true:false;
    }
    @Override
    public void signInPassword(String email,String password) {
            //verify
        List<UsersEntity> usersEntities= usersRepository.findByEmail(email);

        boolean isMatch= passwordEncoder.matches(password,usersEntities.get(0).getPassword());
            if (isMatch)
                System.out.println("Đăng nhập thành công");
            else
                System.out.println("Sai pass");
    }

    @Override
    public void sendVerificationEmail(String email, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String toAddress = email;
        String fromAddress = emailown;
        String senderName = "Booking";
        String subject = "Chỉ cần nhấp chuột để xác nhận";
        String content = "<h1>Xác minh địa chỉ email của bạn</h1>" +
                "Bạn vừa tạo tài khoản với địa chỉ email: [[email]]<br>" +
                "Nhấn nút \"Xác nhận\" để chứng thực địa chỉ email và mở khóa cho toàn bộ tài khoản.<br>" +
                "Chúng tôi cũng sẽ nhập các đặt phòng bạn đã thực hiện với địa chỉ email này.<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">Xác nhận</a></h3>";
        content = content.replace("[[email]]", email);
        System.out.println("verifyURL = " + siteURL);
        content = content.replace("[[URL]]", siteURL);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

}
