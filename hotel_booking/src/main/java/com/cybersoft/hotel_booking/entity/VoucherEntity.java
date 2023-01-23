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

    @Column(name = "voucher_code")
    private String voucherCode;

    @Column(name = "discount_percent")
    private int discountPercent;

    @Column(name = "expired_date")
    @Temporal(TemporalType.DATE)
    private Date expiredDate;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher")
    Set<BookingEntity> bookingEntitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Set<BookingEntity> getBookingEntitySet() {
        return bookingEntitySet;
    }

    public void setBookingEntitySet(Set<BookingEntity> bookingEntitySet) {
        this.bookingEntitySet = bookingEntitySet;
    }
}
