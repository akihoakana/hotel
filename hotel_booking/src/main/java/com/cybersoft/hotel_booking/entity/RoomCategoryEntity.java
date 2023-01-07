package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "room_category")
public class RoomCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "roomCategory")
    private String roomCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "roomCategoryEntity")
    Set<RoomEntity> room;
}
