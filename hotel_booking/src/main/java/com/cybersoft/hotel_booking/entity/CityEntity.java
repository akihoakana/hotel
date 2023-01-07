package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city")
    private String city;

//    @Column(name = "provinceId")
//    private int provinceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "provinceId")
    private ProvinceEntity province;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    Set<HotelEntity> hotelEntitySet;



}
