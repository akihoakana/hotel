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

//    @JsonIgnore
    @OneToMany(mappedBy = "service")
    Set<HotelServiceEntity> hotelServiceEntitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Set<HotelServiceEntity> getHotelServiceEntitySet() {
        return hotelServiceEntitySet;
    }

    public void setHotelServiceEntitySet(Set<HotelServiceEntity> hotelServiceEntitySet) {
        this.hotelServiceEntitySet = hotelServiceEntitySet;
    }
}
