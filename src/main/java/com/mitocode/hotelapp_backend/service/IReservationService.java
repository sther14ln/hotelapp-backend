package com.mitocode.hotelapp_backend.service;

import com.mitocode.hotelapp_backend.model.Reservation;

import java.time.LocalDate;

public interface IReservationService extends ICRUD<Reservation,Integer> {

    boolean existsReservationInDateRange(Integer roomId, LocalDate checkinDate, LocalDate checkoutDate);

}
