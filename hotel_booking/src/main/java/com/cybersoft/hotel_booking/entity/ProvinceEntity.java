package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "province")
public class ProvinceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "province")
    private String province;

    @JsonIgnore
    @OneToMany(mappedBy = "province")
    Set<CityEntity> cityEntitySet;

}
