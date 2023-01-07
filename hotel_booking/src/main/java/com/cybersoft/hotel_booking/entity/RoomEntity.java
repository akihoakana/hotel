package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "roomName")
    private String roomName;

    @Column(name = "maxOccupyAdult")
    private int maxOccupyAdult;

    @Column(name = "maxOccupyChild")
    private int maxOccupyChild;

    @Column(name = "price")
    private float price;

//    @Column(name = "hotelId")
//    private int hotelId;
//
//    @Column(name = "bedCategoryId")
//    private int bedCategoryId;
//
//    @Column(name = "roomCategoryId")
//    private int roomCategoryId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotelId")
    private HotelEntity hotel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bedCategoryId")
    private BedCategoryEntity bedCategoryEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "roomCategoryId")
    private RoomCategoryEntity roomCategoryEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    Set<RoomDatesEntity> roomDatesEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    Set<BookingRoomEntity> bookingRoomEntitySet;

}
