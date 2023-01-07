package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "bill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "bookingId")
//    private int bookingId;
//
//    @Column(name = "userId")
//    private int userId;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "paymentTotal")
    private float paymentTotal;

    @Column(name = "paymentDate")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookingId")
    private BookingEntity booking;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private UsersEntity users;


}
