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

//    @Column(name = "dt")
//    @Temporal(TemporalType.DATE)
//    private Date dt;

    @Column(name = "roomStatus")
    private int roomStatus;

//    @Column(name = "roomId")
//    private int roomId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "roomId")
    private RoomEntity room;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dt")
    private CalendarEntity calendarEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "roomDatesEntity")
    Set<BookingRoomDatesEntity> bookingRoomDatesEntitySet;



}
