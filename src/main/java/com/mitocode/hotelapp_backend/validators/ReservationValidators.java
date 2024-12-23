package com.mitocode.hotelapp_backend.validators;

import com.mitocode.hotelapp_backend.dto.ReservationDto;
import com.mitocode.hotelapp_backend.exception.ValidationException;
import com.mitocode.hotelapp_backend.model.Room;
import com.mitocode.hotelapp_backend.service.IReservationService;
import com.mitocode.hotelapp_backend.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationValidators {

    private final IRoomService roomService;
    private final IReservationService reservationService;


    public void validateSave(ReservationDto dto){
        if(dto.getCheckinDate().isAfter( dto.getCheckoutDate())){
            throw new ValidationException( "La fecha de entrada no puede ser mayor a la fecha de salida");
        }
        Room room = roomService.findById(dto.getRoom().getIdRoom());
        if (room.getAvailable().equals(false)) {
            throw new ValidationException( "La habitación no está disponible");
        }
        boolean estaReservado = reservationService.existsReservationInDateRange(room.getIdRoom(),dto.getCheckinDate(),dto.getCheckoutDate());
        if(estaReservado){
            throw new ValidationException( "Ya existe una reserva para las fecha seleccionadas");
        }
    }



}
