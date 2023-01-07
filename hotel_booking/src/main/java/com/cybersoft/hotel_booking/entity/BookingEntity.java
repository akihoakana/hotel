package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "userId")
//    private int userId;

//    @Column(name = "voucherId")
//    private int voucherId;

    @Column(name = "adultNumber")
    private int adultNumber;

    @Column(name = "childNumber")
    private int childNumber;

    @Column(name = "bookingStatus")
    private String bookingStatus;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "totalPrice")
    private float totalPrice;

    @Column(name = "checkIn")
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Column(name = "checkOut")
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @Column(name = "issuedDate")
    @Temporal(TemporalType.DATE)
    private Date issuedDate;

    @Column(name = "paidDate")
    @Temporal(TemporalType.DATE)
    private Date paidDate;

    @Column(name = "cancelDate")
    @Temporal(TemporalType.DATE)
    private Date cancelDate;

    @Column(name = "paymentDate")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @JsonIgnore
    @OneToMany(mappedBy = "booking")
    Set<BookingRoomEntity> bookingRoomEntitySet;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private UsersEntity users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "voucherId")
    private VoucherEntity voucher;

    @JsonIgnore
    @OneToMany(mappedBy = "booking")
    Set<BookingRoomDatesEntity> bookingRoomDatesEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "booking")
    Set<BillEntity> billEntitySet;

}
