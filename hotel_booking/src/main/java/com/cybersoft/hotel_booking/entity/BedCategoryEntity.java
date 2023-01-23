package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "bed_category")
public class BedCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bed_category")
    private String bedCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "bedCategoryEntity")
    Set<RoomEntity> room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBedCategory() {
        return bedCategory;
    }

    public void setBedCategory(String bedCategory) {
        this.bedCategory = bedCategory;
    }

    public Set<RoomEntity> getRoom() {
        return room;
    }

    public void setRoom(Set<RoomEntity> room) {
        this.room = room;
    }
}
