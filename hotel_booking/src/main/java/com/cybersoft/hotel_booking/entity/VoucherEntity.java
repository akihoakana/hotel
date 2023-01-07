package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "voucher")
public class VoucherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "voucherCode")
    private String voucherCode;

    @Column(name = "discountPercent")
    private int discountPercent;

    @Column(name = "expiredDate")
    @Temporal(TemporalType.DATE)
    private Date expiredDate;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher")
    Set<BookingEntity> bookingEntitySet;


}
