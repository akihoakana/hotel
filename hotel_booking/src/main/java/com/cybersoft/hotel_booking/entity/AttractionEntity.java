package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "attraction")
public class AttractionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "distance")
    private float distance;

    @Column(name = "image")
    private String image ;

//    @Column(name = "hotelId")
//    private int hotelId  ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotelId")
    private HotelEntity hotel;




}
