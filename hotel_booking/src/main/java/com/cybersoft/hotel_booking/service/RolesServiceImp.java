package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.RolesEntity;
import com.cybersoft.hotel_booking.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImp implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;


    @Override
    public RolesEntity getRole(String roleName) {
        return rolesRepository.findByRoleName(roleName);
    }
}
