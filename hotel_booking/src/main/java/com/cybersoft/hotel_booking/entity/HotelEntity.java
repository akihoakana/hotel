package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "hotel")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "hotel_rank")
    private int hotelRank;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    Set<HotelServiceEntity> hotelServiceEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    Set<AttractionEntity> attractionEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    Set<ReviewEntity> reviewEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    Set<RoomEntity> roomEntitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHotelRank() {
        return hotelRank;
    }

    public void setHotelRank(int hotelRank) {
        this.hotelRank = hotelRank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public Set<HotelServiceEntity> getHotelServiceEntitySet() {
        return hotelServiceEntitySet;
    }

    public void setHotelServiceEntitySet(Set<HotelServiceEntity> hotelServiceEntitySet) {
        this.hotelServiceEntitySet = hotelServiceEntitySet;
    }

    public Set<AttractionEntity> getAttractionEntitySet() {
        return attractionEntitySet;
    }

    public void setAttractionEntitySet(Set<AttractionEntity> attractionEntitySet) {
        this.attractionEntitySet = attractionEntitySet;
    }

    public Set<ReviewEntity> getReviewEntitySet() {
        return reviewEntitySet;
    }

    public void setReviewEntitySet(Set<ReviewEntity> reviewEntitySet) {
        this.reviewEntitySet = reviewEntitySet;
    }

    public Set<RoomEntity> getRoomEntitySet() {
        return roomEntitySet;
    }

    public void setRoomEntitySet(Set<RoomEntity> roomEntitySet) {
        this.roomEntitySet = roomEntitySet;
    }
}
