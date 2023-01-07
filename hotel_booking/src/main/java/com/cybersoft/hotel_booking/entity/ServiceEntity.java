package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "service")
    private String service;

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    Set<HotelServiceEntity> hotelServiceEntitySet;


}
