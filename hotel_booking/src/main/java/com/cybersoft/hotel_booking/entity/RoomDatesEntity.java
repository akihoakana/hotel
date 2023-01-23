package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "room_dates")
public class RoomDatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_status")
    private int roomStatus;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dt")
    private CalendarEntity calendarEntity;

////    @JsonIgnore
//    @OneToMany(mappedBy = "roomDatesEntity")
//    Set<BookingRoomDatesEntity> bookingRoomDatesEntitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public CalendarEntity getCalendarEntity() {
        return calendarEntity;
    }

    public void setCalendarEntity(CalendarEntity calendarEntity) {
        this.calendarEntity = calendarEntity;
    }

//    public Set<BookingRoomDatesEntity> getBookingRoomDatesEntitySet() {
//        return bookingRoomDatesEntitySet;
//    }
//
//    public void setBookingRoomDatesEntitySet(Set<BookingRoomDatesEntity> bookingRoomDatesEntitySet) {
//        this.bookingRoomDatesEntitySet = bookingRoomDatesEntitySet;
//    }
}
