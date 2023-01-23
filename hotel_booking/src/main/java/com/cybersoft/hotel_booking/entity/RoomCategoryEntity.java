package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "room_category")
public class RoomCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_category")
    private String roomCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "roomCategoryEntity")
    Set<RoomEntity> room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Set<RoomEntity> getRoom() {
        return room;
    }

    public void setRoom(Set<RoomEntity> room) {
        this.room = room;
    }
}
