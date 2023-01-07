package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "hotel_service")
public class HotelServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotelId")
    private HotelEntity hotel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "serviceId")
    private ServiceEntity service;




}
