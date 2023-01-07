package com.cybersoft.hotel_booking.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignInRequest {
    @NotBlank(message = "Vui lòng nhập email") @Email
    private String email;
    @NotBlank(message = "Vui lòng nhập password")
    @Pattern(regexp = "\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{10,}$\"\n"
            ,message = "Dùng ít nhất 10 ký tự, trong đó có chữ hoa, chữ thường và số.\n" +
            "\n")
    private String password;
    @NotBlank(message = "Vui lòng nhập xác nhận password")
    private String passwordConfirm;
}
