package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "max_occupy_adult")
    private int maxOccupyAdult;

    @Column(name = "max_occupy_child")
    private int maxOccupyChild;

    @Column(name = "price")
    private float price;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bed_category_id")
    private BedCategoryEntity bedCategoryEntity;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_category_id")
    private RoomCategoryEntity roomCategoryEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    Set<RoomDatesEntity> roomDatesEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    Set<BookingRoomEntity> bookingRoomEntitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getMaxOccupyAdult() {
        return maxOccupyAdult;
    }

    public void setMaxOccupyAdult(int maxOccupyAdult) {
        this.maxOccupyAdult = maxOccupyAdult;
    }

    public int getMaxOccupyChild() {
        return maxOccupyChild;
    }

    public void setMaxOccupyChild(int maxOccupyChild) {
        this.maxOccupyChild = maxOccupyChild;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public BedCategoryEntity getBedCategoryEntity() {
        return bedCategoryEntity;
    }

    public void setBedCategoryEntity(BedCategoryEntity bedCategoryEntity) {
        this.bedCategoryEntity = bedCategoryEntity;
    }

    public RoomCategoryEntity getRoomCategoryEntity() {
        return roomCategoryEntity;
    }

    public void setRoomCategoryEntity(RoomCategoryEntity roomCategoryEntity) {
        this.roomCategoryEntity = roomCategoryEntity;
    }

    public Set<RoomDatesEntity> getRoomDatesEntitySet() {
        return roomDatesEntitySet;
    }

    public void setRoomDatesEntitySet(Set<RoomDatesEntity> roomDatesEntitySet) {
        this.roomDatesEntitySet = roomDatesEntitySet;
    }

    public Set<BookingRoomEntity> getBookingRoomEntitySet() {
        return bookingRoomEntitySet;
    }

    public void setBookingRoomEntitySet(Set<BookingRoomEntity> bookingRoomEntitySet) {
        this.bookingRoomEntitySet = bookingRoomEntitySet;
    }
}
