package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "bed_category")
public class BedCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bedCategory")
    private String bedCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "bedCategoryEntity")
    Set<RoomEntity> room;
}
