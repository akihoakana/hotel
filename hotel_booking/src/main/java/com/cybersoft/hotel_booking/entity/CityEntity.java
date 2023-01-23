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


//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    Set<HotelEntity> hotelEntitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    public Set<HotelEntity> getHotelEntitySet() {
        return hotelEntitySet;
    }

    public void setHotelEntitySet(Set<HotelEntity> hotelEntitySet) {
        this.hotelEntitySet = hotelEntitySet;
    }
}
