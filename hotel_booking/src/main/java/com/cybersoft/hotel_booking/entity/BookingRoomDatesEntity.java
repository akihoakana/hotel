package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "booking_room_dates")
public class BookingRoomDatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "roomDatesId")
//    private int roomDatesId;

    @Column(name = "roomId")
    private int roomId;

    @Column(name = "dt")
    @Temporal(TemporalType.DATE)
    private Date dt;

    @Column(name = "roomStatus")
    private int roomStatus;

//    @Column(name = "bookingId")
//    private int bookingId;

    @Column(name = "checkIn")
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Column(name = "checkOut")
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "roomDatesId")
    private RoomDatesEntity roomDatesEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookingId")
    private BookingEntity booking;



}
