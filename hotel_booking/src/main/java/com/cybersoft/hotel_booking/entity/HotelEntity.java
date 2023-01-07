package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "hotel")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hotelName")
    private String hotelName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "hotelRank")
    private int hotelRank;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

//    @Column(name = "cityId")
//    private int cityId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cityId")
    private CityEntity city;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    Set<HotelServiceEntity> hotelServiceEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    Set<AttractionEntity> attractionEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    Set<ReviewEntity> reviewEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    Set<RoomEntity> room;



}
