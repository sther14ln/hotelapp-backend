package com.mitocode.hotelapp_backend.repo;

import com.mitocode.hotelapp_backend.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IReservationRepo extends IGenericRepo<Reservation,Integer>{

    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END
        FROM Reservation r
        WHERE r.room.idRoom = :roomId
          AND (:checkinDate BETWEEN r.checkinDate AND r.checkoutDate
               OR :checkoutDate BETWEEN r.checkinDate AND r.checkoutDate
               OR r.checkinDate BETWEEN :checkinDate AND :checkoutDate
               OR r.checkoutDate BETWEEN :checkinDate AND :checkoutDate)
    """)
    boolean existsReservationInDateRange(
            @Param("roomId") Integer roomId,
            @Param("checkinDate") LocalDate checkinDate,
            @Param("checkoutDate") LocalDate checkoutDate
    );
}
