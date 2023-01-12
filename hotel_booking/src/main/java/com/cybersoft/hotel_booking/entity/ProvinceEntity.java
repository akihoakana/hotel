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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Set<CityEntity> getCityEntitySet() {
        return cityEntitySet;
    }

    public void setCityEntitySet(Set<CityEntity> cityEntitySet) {
        this.cityEntitySet = cityEntitySet;
    }
}
