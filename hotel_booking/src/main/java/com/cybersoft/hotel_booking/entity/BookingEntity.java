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

    @Column(name = "adult_number")
    private int adultNumber;

    @Column(name = "child_number")
    private int childNumber;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "check_in")
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Column(name = "check_out")
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @Column(name = "issued_date")
    @Temporal(TemporalType.DATE)
    private Date issuedDate;

    @Column(name = "paid_date")
    @Temporal(TemporalType.DATE)
    private Date paidDate;

    @Column(name = "cancel_date")
    @Temporal(TemporalType.DATE)
    private Date cancelDate;

    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

//    @JsonIgnore
    @OneToMany(mappedBy = "booking")
    Set<BookingRoomEntity> bookingRoomEntitySet;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity users;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private VoucherEntity voucher;

//    @JsonIgnore
    @OneToMany(mappedBy = "booking")
    Set<BookingRoomDatesEntity> bookingRoomDatesEntitySet;

    @JsonIgnore
    @OneToOne(mappedBy = "booking")
    BillEntity billEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public int getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Set<BookingRoomEntity> getBookingRoomEntitySet() {
        return bookingRoomEntitySet;
    }

    public void setBookingRoomEntitySet(Set<BookingRoomEntity> bookingRoomEntitySet) {
        this.bookingRoomEntitySet = bookingRoomEntitySet;
    }

    public UsersEntity getUsers() {
        return users;
    }

    public void setUsers(UsersEntity users) {
        this.users = users;
    }

    public VoucherEntity getVoucher() {
        return voucher;
    }

    public void setVoucher(VoucherEntity voucher) {
        this.voucher = voucher;
    }

    public Set<BookingRoomDatesEntity> getBookingRoomDatesEntitySet() {
        return bookingRoomDatesEntitySet;
    }

    public void setBookingRoomDatesEntitySet(Set<BookingRoomDatesEntity> bookingRoomDatesEntitySet) {
        this.bookingRoomDatesEntitySet = bookingRoomDatesEntitySet;
    }

    public BillEntity getBillEntity() {
        return billEntity;
    }

    public void setBillEntitySet(BillEntity billEntity) {
        this.billEntity = billEntity;
    }
}
