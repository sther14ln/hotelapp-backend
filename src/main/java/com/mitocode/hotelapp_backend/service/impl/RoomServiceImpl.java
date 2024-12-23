package com.mitocode.hotelapp_backend.service.impl;

import com.mitocode.hotelapp_backend.model.Room;
import com.mitocode.hotelapp_backend.repo.IGenericRepo;
import com.mitocode.hotelapp_backend.repo.IRoomRepo;
import com.mitocode.hotelapp_backend.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends CRUDImpl<Room, Integer> implements IRoomService {

    private final IRoomRepo repo;

    @Override
    protected IGenericRepo<Room, Integer> getRepo() {
        return repo;
    }


}
