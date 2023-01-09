package com.cybersoft.hotel_booking.repository;

import com.cybersoft.hotel_booking.entity.TokenExpiredEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public interface TokenRepository extends JpaRepository<TokenExpiredEntity,Integer> {
    public TokenExpiredEntity findByName(String token);
}
