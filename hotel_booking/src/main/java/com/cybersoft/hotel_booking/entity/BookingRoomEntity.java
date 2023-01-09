package com.cybersoft.hotel_booking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "booking_room")
public class BookingRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "check_in")
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Column(name = "check_out")
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "price")
    private float price;

    @Column(name = "offer_status")
    private int offerStatus;

    @Column(name = "chosen_status")
    private int chosenStatus;

    @Column(name = "hotel_id")
    private int hotelId;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(int offerStatus) {
        this.offerStatus = offerStatus;
    }

    public int getChosenStatus() {
        return chosenStatus;
    }

    public void setChosenStatus(int chosenStatus) {
        this.chosenStatus = chosenStatus;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }
}
