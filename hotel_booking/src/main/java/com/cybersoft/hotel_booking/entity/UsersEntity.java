package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date DOB; //private java.util.Date DOB;
    //https://stackoverflow.com/questions/24650186/choosing-between-java-util-date-or-java-sql-date#:~:text=When%20reading%20or%20writing%20to%20a%20database%E2%80%99s%20date-only,but%20need%20a%20java.util.Date%2C%20simply%20pass%20the%20java.sql.Date.

    @Column(name = "email")
    private String email;

    @Column(name = "email_verify")
    private boolean emailVerify;

    @Column(name = "password")
    private String password;

    @Column(name = "token_google")
    private String tokenGoogle;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

//    @Column(name = "roleId")
//    private int roleId;

    @JsonIgnore
    @ManyToOne
    @MapsId
    @JoinColumn(name = "roleId")
    private RolesEntity roles;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    Set<ReviewEntity> reviewEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    Set<BookingEntity> bookingEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    Set<BillEntity> billEntitySet;

}
