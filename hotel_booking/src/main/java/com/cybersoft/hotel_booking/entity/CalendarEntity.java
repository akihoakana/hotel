package com.cybersoft.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "calendar_table")
public class CalendarEntity {

    @Id
    @Column(name = "dt")
    @Temporal(TemporalType.DATE)
    private Date dt;

    @Column(name = "userId")
    private int userId;

    @Column(name = "y")
    private int y;

    @Column(name = "q")
    private int q;

    @Column(name = "m")
    private int m;

    @Column(name = "d")
    private int d;

    @Column(name = "dw")
    private int dw;

    @Column(name = "w")
    private int w;

    @Column(name = "monthName")
    private String monthName;

    @Column(name = "dayName")
    private String dayName;

    @Column(name = "isWeekday")
    private boolean isWeekday;

    @Column(name = "isHoliday")
    private boolean isHoliday;

    @Column(name = "holidayDescr")
    private String holidayDescr;

    @Column(name = "isPayday")
    private boolean isPayday;

    @JsonIgnore
    @OneToMany(mappedBy = "calendarEntity")
    Set<RoomDatesEntity> roomDatesEntitySet;
}
