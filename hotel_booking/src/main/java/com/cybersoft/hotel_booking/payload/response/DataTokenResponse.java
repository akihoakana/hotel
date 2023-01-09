package com.cybersoft.hotel_booking.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTokenResponse {
    private String token;
    private String refreshToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
