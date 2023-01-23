package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
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

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RolesEntity roles;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    Set<ReviewEntity> reviewEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    Set<BookingEntity> bookingEntitySet;

//    @JsonIgnore
//    @OneToMany(mappedBy = "users")
//    Set<BillEntity> billEntitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(boolean emailVerify) {
        this.emailVerify = emailVerify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RolesEntity getRoles() {
        return roles;
    }

    public void setRoles(RolesEntity roles) {
        this.roles = roles;
    }

    public Set<ReviewEntity> getReviewEntitySet() {
        return reviewEntitySet;
    }

    public void setReviewEntitySet(Set<ReviewEntity> reviewEntitySet) {
        this.reviewEntitySet = reviewEntitySet;
    }

    public Set<BookingEntity> getBookingEntitySet() {
        return bookingEntitySet;
    }

    public void setBookingEntitySet(Set<BookingEntity> bookingEntitySet) {
        this.bookingEntitySet = bookingEntitySet;
    }

//    public Set<BillEntity> getBillEntitySet() {
//        return billEntitySet;
//    }
//
//    public void setBillEntitySet(Set<BillEntity> billEntitySet) {
//        this.billEntitySet = billEntitySet;
//    }
}
