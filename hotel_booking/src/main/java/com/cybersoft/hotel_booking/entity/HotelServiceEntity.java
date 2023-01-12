package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "hotel_service")
public class HotelServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }
}
