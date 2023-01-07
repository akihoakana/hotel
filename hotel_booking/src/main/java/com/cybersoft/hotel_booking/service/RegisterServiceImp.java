package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.payload.request.RegisterRequest;
import com.cybersoft.hotel_booking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
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

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public UsersEntity checkLogin(String email) {
        List<UsersEntity> users = usersRepository.findByEmail(email);

        return users.size() > 0 ? users.get(0) : null;
    }
    @Override
    public UsersEntity registerNewUserAccount(RegisterRequest registerRequest, String siteURL) throws UnsupportedEncodingException, MessagingException {
            UsersEntity UsersEntity =new UsersEntity();
            UsersEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            UsersEntity.setEmail(registerRequest.getEmail());
//            String randomCode = RandomString.make(12);
//            UsersEntity.setVerifyCode(randomCode);
//            UsersEntity.setVerifyCodeExpired(new Timestamp(System.currentTimeMillis()+expiredDate));
            System.out.println("new Timestamp(System.currentTimeMillis()+expiredDate) = "
                    + new Timestamp(System.currentTimeMillis()+expiredDate));
            System.out.println("Đăng kí thành công");
            sendVerificationEmail(registerRequest.getEmail(), siteURL);
            return usersRepository.save(UsersEntity);

    }
//
    @Override
    public UsersEntity confirmByEmail(String email) {
        List<UsersEntity> usersEntities= usersRepository.findByEmail(email+"@gmail.com");
        if (usersEntities.size()>0){
//            if (UsersEntity.getVerifyCodeExpired().after(new Timestamp(System.currentTimeMillis()))){
//                UsersEntity.set(true);
//                usersRepository.save(UsersEntity);
//                return "Active thành công";
//            }
            usersEntities.get(0).setEmailVerify(true);
            usersRepository.save(usersEntities.get(0));
            return usersRepository.save(usersEntities.get(0));
//            return "Active thất bại";
        }
        else
            return null;

    }


    @Override
    public List<UsersEntity> emailExists(String email) {
        List<UsersEntity> usersEntities= usersRepository.findByEmail(email);
        return  (usersEntities.size()>0)?usersEntities:null;
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
        String nameOnly = email.substring(0,email.indexOf('@'));
        String verifyURL = siteURL + "/register/verifyemail/"+nameOnly;
        content = content.replace("[[URL]]", verifyURL);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

}
