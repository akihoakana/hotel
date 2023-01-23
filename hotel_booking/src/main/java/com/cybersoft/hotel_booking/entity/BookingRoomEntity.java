package com.cybersoft.hotel_booking.entity;

import com.cybersoft.hotel_booking.DTO.HotelSearchDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@NamedNativeQuery(name = "BookingRoomEntity.findBookingRoomByHotelIdAndAndBookingId" ,
        query= " SELECT "  +
                " hotel.hotel_name,"  +
                " room.id as room_id, room.max_occupy_adult, room.max_occupy_child, "  +
                " :checkIn AS check_in,"  +
                " :checkOut AS check_out,"  +
                " room_category.room_category,  "  +
                " bed_category.bed_category,"  +
                " room.price * datediff(:checkOut,:checkIn) AS sub_total_price  "  +
                " FROM hotel"  +
                " INNER JOIN room"  +
                " ON room.hotel_id = hotel.id"  +
                " INNER JOIN room_dates"  +
                " ON room.id = room_dates.room_id"  +
                " INNER JOIN bed_category"  +
                " ON bed_category.id = room.bed_category_id"  +
                " INNER JOIN room_category"  +
                " ON room_category.id = room.room_category_id"  +
                " WHERE hotel_id = :hotelId "  +
                " AND room.max_occupy_adult >= :maxOccupyAdult "  +
                " AND room.max_occupy_child  >= :maxOccupyChild "  +
                " AND room_dates.dt >= :checkIn "  +
                " AND room_dates.dt < :checkOut "  +
                " group by 1,2,3,4,5,6,7,8"  +
                " HAVING  SUM(room_dates.room_status) = COUNT(room_dates.room_status)" ,
        resultSetMapping = " 1" )
@SqlResultSetMapping(name = " 1" ,
        classes = @ConstructorResult(targetClass = HotelSearchDTO.class,
                columns = {
                        @ColumnResult(name = " hotel_name" ),
                        @ColumnResult(name = " room_id" ,type = int.class),
                        @ColumnResult(name = " max_occupy_adult" ,type = int.class),
                        @ColumnResult(name = " max_occupy_child" ,type = int.class),
                        @ColumnResult(name = " check_in" ),
                        @ColumnResult(name = " check_out" ),
                        @ColumnResult(name = " room_category" ),
                        @ColumnResult(name = " bed_category" ),
                        @ColumnResult(name = " sub_total_price" ,type = double.class)
                }))
@Entity(name = " booking_room" )
public class BookingRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = " check_in" )
//    @Temporal(TemporalType.DATE)
//    private Date checkIn;
//    @Column(name = " check_out" )
//    @Temporal(TemporalType.DATE)
//    private Date checkOut;
//
//    @Column(name = " room_name" )
//    private String roomName;
//
//    @Column(name = " price" )
//    private float price;
//
//    @Column(name = " offer_status" )
//    private int offerStatus;

//    @Column(name = " chosen_status" )
//    private int chosenStatus;

//    @Column(name = " hotel_id" )
//    private int hotelId;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = " booking_id" )
    private BookingEntity booking;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = " room_id" )
    private RoomEntity room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
//    public String getRoomName() {
//        return roomName;
//    }
//
//    public void setRoomName(String roomName) {
//        this.roomName = roomName;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }
//
//    public int getOfferStatus() {
//        return offerStatus;
//    }
//
//    public void setOfferStatus(int offerStatus) {
//        this.offerStatus = offerStatus;
//    }

//    public int getChosenStatus() {
//        return chosenStatus;
//    }
//
//    public void setChosenStatus(int chosenStatus) {
//        this.chosenStatus = chosenStatus;
//    }

//    public int getHotelId() {
//        return hotelId;
//    }
//
//    public void setHotelId(int hotelId) {
//        this.hotelId = hotelId;
//    }

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
