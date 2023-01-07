package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "booking_room")
public class BookingRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "checkIn")
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Column(name = "checkOut")
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @Column(name = "roomName")
    private String roomName;

    @Column(name = "price")
    private float price;

    @Column(name = "offerStatus")
    private int offerStatus;

    @Column(name = "chosenStatus")
    private int chosenStatus;

    @Column(name = "hotelId")
    private int hotelId;

//    @Column(name = "bookingId")
//    private int bookingId;

//    @Column(name = "roomId")
//    private int roomId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookingId")
    private BookingEntity booking;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "roomId")
    private RoomEntity room;






}
