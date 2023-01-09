package com.cybersoft.hotel_booking.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class NewPasswordRequest {
    @NotBlank(message = "Vui lòng nhập password cũ")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
            ,message = "Dùng ít nhất 8 ký tự, trong đó có chữ hoa, chữ thường , ký tự đặc biệt và số.")
    private String password;
    @NotBlank(message = "Vui lòng nhập password mới")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
            ,message = "Dùng ít nhất 8 ký tự, trong đó có chữ hoa, chữ thường , ký tự đặc biệt và số.")
    private String passwordConfirm;
}