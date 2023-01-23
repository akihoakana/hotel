//package com.cybersoft.hotel_booking.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity(name = "bill")
//public class BillEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//
//    @Column(name = "full_name")
//    private String fullName;
//
//    @Column(name = "payment_method")
//    private String paymentMethod;
//
//    @Column(name = "payment_total")
//    private float paymentTotal;
//
//    @Column(name = "payment_date")
//    @Temporal(TemporalType.DATE)
//    private Date paymentDate;
//
////    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "booking_id")
//    private BookingEntity booking;
//
////    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "users_id")
//    private UsersEntity users;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//
//    public float getPaymentTotal() {
//        return paymentTotal;
//    }
//
//    public void setPaymentTotal(float paymentTotal) {
//        this.paymentTotal = paymentTotal;
//    }
//
//    public Date getPaymentDate() {
//        return paymentDate;
//    }
//
//    public void setPaymentDate(Date paymentDate) {
//        this.paymentDate = paymentDate;
//    }
//
//    public BookingEntity getBooking() {
//        return booking;
//    }
//
//    public void setBooking(BookingEntity booking) {
//        this.booking = booking;
//    }
//
//    public UsersEntity getUsers() {
//        return users;
//    }
//
//    public void setUsers(UsersEntity users) {
//        this.users = users;
//    }
//}
