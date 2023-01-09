package com.cybersoft.hotel_booking.repository;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Integer> {
    List<UsersEntity> findByEmail(String email);
}
