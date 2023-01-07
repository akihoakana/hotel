package com.cybersoft.hotel_booking.entity;

import javax.persistence.*;

@Entity(name = "offer_status")
public class OfferStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "offerStatus")
    private int offerStatus;

    @Column(name = "bookingId")
    private int bookingId;

    @Column(name = "roomId")
    private int roomId;

}
