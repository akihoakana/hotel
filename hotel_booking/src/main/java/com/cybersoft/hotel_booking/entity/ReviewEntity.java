package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content ;

    @Column(name = "rateScore")
    private int rateScore  ;

    @Column(name = "createdDate")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

//    @Column(name = "hotelId")
//    private int hotelId  ;
//
//    @Column(name = "usersId")
//    private int usersId ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotelId")
    private HotelEntity hotel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private UsersEntity users;






}
