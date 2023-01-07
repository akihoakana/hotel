package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.repository.UsersRepository;
import net.bytebuddy.utility.RandomString;
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
import java.sql.Timestamp;
import java.util.List;

@Service
public class SigninServiceImp implements  SigninService{
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${spring.mail.username}")
    private String emailown;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public boolean newPassord(String email, String password,String passwordConfirm) {
        List<UsersEntity> usersEntities= usersRepository.findByEmail(email);
        if (usersEntities.size()>0 && StringUtils.hasText(password) && StringUtils.hasText(passwordConfirm)) {
            if (password.equals(passwordConfirm)){

                usersEntities.get(0).setPassword(passwordEncoder.encode(password));
                System.out.println("usersEntities.get(0).getPassword() = " + usersEntities.get(0).getPassword());
                usersRepository.saveAll(usersEntities);
                System.out.println("newPassord thành công");
                return true;
            }
            else
                return false;
        }
        else{
            System.out.println("newPassord thất bại");
            return false;
        }
    }


//    @Override
//    public List<GrantedAuthority> getRoles(String email) {
//        List<RoleEntity> list=new ArrayList<>();
//        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
//        System.out.println("usersRepository.findByEmail(email) = " + usersRepository.findByEmail(email).get(0).getUserRoleEntity());
//        Set<UserRoleEntity> userRoleEntities=usersRepository.findByEmail(email).get(0).getUserRoleEntity();
//        System.out.println("userRoleEntities = " + userRoleEntities);
//        for (UserRoleEntity userRoleEntity:userRoleEntities){
//            RoleEntity roleEntity =userRoleEntity.getRoleEntity();
//            System.out.println("roleEntity = " + roleEntity);
//            list.add(roleEntity);
//        }
//        for (RoleEntity roleEntity:list){
//            authList.add(new SimpleGrantedAuthority(roleEntity.getName()));
//            System.out.println("authList = " + authList);
//            System.out.println("roleEntity.getName() = " + roleEntity.getName());
//        }
//        return authList;
//    }
//    @Override
//    public List<RoleEntity> getRoles(String email) {
//        List<RoleEntity> list=new ArrayList<>();
//        Set<UserRoleEntity> userRoleEntities=usersRepository.findByEmail(email).get(0).getUserRoleEntity();
//        for (UserRoleEntity userRoleEntity:userRoleEntities){
//            RoleEntity roleEntity =userRoleEntity.getRoleEntity();
//            list.add(roleEntity);
//        }
//        return list;
//    }

    @Override
    public void sendForgetPass(String email, String siteURL) throws UnsupportedEncodingException, MessagingException {
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
        String verifyURL = siteURL + "/signin/newpassword";
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
