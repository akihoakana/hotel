//package com.cybersoft.hotel_booking.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity(name = "booking_room_dates")
//public class BookingRoomDatesEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
////    @Column(name = "roomDatesId")
////    private int roomDatesId;
//
//    @Column(name = "room_id")
//    private int roomId;
//
//    @Column(name = "dt")
//    @Temporal(TemporalType.DATE)
//    private Date dt;
//
//    @Column(name = "room_status")
//    private int roomStatus;
//
////    @Column(name = "bookingId")
////    private int bookingId;
//
//    @Column(name = "check_in")
//    @Temporal(TemporalType.DATE)
//    private Date checkIn;
//
//    @Column(name = "check_out")
//    @Temporal(TemporalType.DATE)
//    private Date checkOut;
//
////    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "room_dates_id")
//    private RoomDatesEntity roomDatesEntity;
//
////    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "booking_id")
//    private BookingEntity booking;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getRoomId() {
//        return roomId;
//    }
//
//    public void setRoomId(int roomId) {
//        this.roomId = roomId;
//    }
//
//    public Date getDt() {
//        return dt;
//    }
//
//    public void setDt(Date dt) {
//        this.dt = dt;
//    }
//
//    public int getRoomStatus() {
//        return roomStatus;
//    }
//
//    public void setRoomStatus(int roomStatus) {
//        this.roomStatus = roomStatus;
//    }
//
//    public Date getCheckIn() {
//        return checkIn;
//    }
//
//    public void setCheckIn(Date checkIn) {
//        this.checkIn = checkIn;
//    }
//
//    public Date getCheckOut() {
//        return checkOut;
//    }
//
//    public void setCheckOut(Date checkOut) {
//        this.checkOut = checkOut;
//    }
//
//    public RoomDatesEntity getRoomDatesEntity() {
//        return roomDatesEntity;
//    }
//
//    public void setRoomDatesEntity(RoomDatesEntity roomDatesEntity) {
//        this.roomDatesEntity = roomDatesEntity;
//    }
//
//    public BookingEntity getBooking() {
//        return booking;
//    }
//
//    public void setBooking(BookingEntity booking) {
//        this.booking = booking;
//    }
//}
