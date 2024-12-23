package com.mitocode.hotelapp_backend.controller;

import com.mitocode.hotelapp_backend.dto.ReservationDto;
import com.mitocode.hotelapp_backend.dto.RoomDto;
import com.mitocode.hotelapp_backend.exception.CustomErrorRecord;
import com.mitocode.hotelapp_backend.exception.ValidationException;
import com.mitocode.hotelapp_backend.model.Reservation;
import com.mitocode.hotelapp_backend.model.Room;
import com.mitocode.hotelapp_backend.service.IReservationService;
import com.mitocode.hotelapp_backend.service.IRoomService;
import com.mitocode.hotelapp_backend.util.MapperUtil;
import com.mitocode.hotelapp_backend.validators.ReservationValidators;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
@CrossOrigin(originPatterns = "*")
public class ReservationController {

    private final  IRoomService roomService;
    private final IReservationService service;
    private final MapperUtil mapper;
    private final ModelMapper modelMapper;
    private final ReservationValidators reservationValidators;

    @GetMapping
    public ResponseEntity<List<ReservationDto>> findAll() {

        List<ReservationDto> list = mapper.mapList(service.findAll(), ReservationDto.class);
        return ResponseEntity.ok(list);//controlar la respuesta del servidor a 201 y no solo 200
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ReservationDto dto){
        reservationValidators.validateSave(dto);
        Reservation obj1 = service.save(mapper.map(dto, Reservation.class));
        dto.getRoom().setAvailable(false);
        roomService.update(dto.getRoom().getIdRoom(),mapper.map(dto.getRoom(),Room.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj1.getIdReservation()).toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> findById(@PathVariable("id") Integer id) {
        Reservation obj = service.findById(id);
        return ResponseEntity.ok(mapper.map(obj, ReservationDto.class));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> update(@PathVariable("id") Integer id, @Valid @RequestBody ReservationDto dto){
        Reservation obj = service.update(id, mapper.map(dto, Reservation.class));
        return ResponseEntity.ok(mapper.map(obj, ReservationDto.class));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }


}

