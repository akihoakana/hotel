package com.cybersoft.hotel_booking.entity;

import com.cybersoft.hotel_booking.DTO.HotelSearchDTO;

import javax.persistence.*;
import java.util.Date;

@NamedNativeQuery(name = "BookingRoomEntity.findBookingRoomByHotelIdAndAndBookingId",
        query= " SELECT hotel_id, hotel_name, address, description, image, hotel_rank, avg_rate_score, rate_count, "  +
                " booking_id, room_id, max_occupy_adult, max_occupy_child, price, room_category, bed_category "  +
                " FROM "  +
                " (SELECT hotel.hotel_id, hotel.hotel_name, hotel.address, hotel.description, hotel.image, hotel.hotel_rank, hotel.avg_rate_score, hotel.rate_count "  +
                " , booking.id as booking_id "  +
                " , room.id as room_id, room.max_occupy_adult, room.max_occupy_child, room.price, room_category.room_category,  bed_category.bed_category "  +
                " , room_dates.room_status "  +
                " , SUM(room_dates.room_status) OVER (PARTITION BY booking.id, room.id) AS sum_status "  +
                " , count(room_dates.room_status) OVER (PARTITION BY booking.id, room.id) AS count_status "  +
                " FROM "  +
                " (SELECT h.id AS hotel_id "  +
                " , h.hotel_name "  +
                " , h.address "  +
                " , h.description "  +
                " , h.image "  +
                " , h.hotel_rank "  +
                " , avg(rv.rate_score) AS avg_rate_score  "  +
                " , count(rv.rate_score) AS rate_count  "  +
                " FROM hotel AS h  "  +
                " INNER JOIN review AS rv "  +
                " ON h.id = rv.hotel_id "  +
                " WHERE h.id = :hotelId "  +
                " GROUP BY 1,2,3,4,5,6) AS hotel "  +
                " INNER JOIN room "  +
                " ON room.hotel_id = hotel.hotel_id "  +
                " CROSS JOIN booking "  +
                " INNER JOIN room_dates "  +
                " ON room.id = room_dates.room_id "  +
                " INNER JOIN bed_category "  +
                " ON bed_category.id = room.bed_category_id "  +
                " INNER JOIN room_category "  +
                " ON room_category.id = room.room_category_id "  +
                " WHERE booking.id = :bookingId  "  +
                " AND room.max_occupy_adult >= booking.adult_number "  +
                " AND room.max_occupy_adult  >= booking.child_number "  +
                " AND room_dates.dt >= booking.check_in "  +
                " AND   room_dates.dt < booking.check_out "  +
                " ) AS b "  +
                " WHERE b.sum_status = b.count_status "  +
                " GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14",
        resultSetMapping = "1")
@SqlResultSetMapping(name = "1",
        classes = @ConstructorResult(targetClass = HotelSearchDTO.class,
                columns = {@ColumnResult(name = "hotel_id",type = int.class),
                        @ColumnResult(name = "hotel_name"),
                        @ColumnResult(name = "address"),
                        @ColumnResult(name = "description"),
                        @ColumnResult(name = "image"),
                        @ColumnResult(name = "hotel_rank",type = int.class),
                        @ColumnResult(name = "avg_rate_score",type = double.class),
                        @ColumnResult(name = "rate_count",type = int.class),
                        @ColumnResult(name = "booking_id",type = int.class),
                        @ColumnResult(name = "room_id",type = int.class),
                        @ColumnResult(name = "max_occupy_adult",type = int.class),
                        @ColumnResult(name = "max_occupy_child",type = int.class),
                        @ColumnResult(name = "price",type = double.class),
                        @ColumnResult(name = "room_category"),
                        @ColumnResult(name = "bed_category")}))
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
