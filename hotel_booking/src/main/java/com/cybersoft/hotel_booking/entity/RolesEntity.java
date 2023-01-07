package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "roles")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "roleName")
    private String roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "roles")
    Set<UsersEntity> usersEntitySet;





}
