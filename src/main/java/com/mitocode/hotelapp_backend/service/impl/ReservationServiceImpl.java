package com.mitocode.hotelapp_backend.service.impl;

import com.mitocode.hotelapp_backend.model.Reservation;
import com.mitocode.hotelapp_backend.repo.IGenericRepo;
import com.mitocode.hotelapp_backend.repo.IReservationRepo;
import com.mitocode.hotelapp_backend.service.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl extends CRUDImpl<Reservation,Integer> implements IReservationService {

    private final IReservationRepo repo;

    @Override
    protected IGenericRepo<Reservation, Integer> getRepo() {
        return repo;

    }

    @Override
    public boolean existsReservationInDateRange(Integer roomId, LocalDate checkinDate, LocalDate checkoutDate) {
        return repo.existsReservationInDateRange(roomId,checkinDate,checkoutDate);
    }
}
