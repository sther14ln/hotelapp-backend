package com.mitocode.hotelapp_backend.controller;

import com.mitocode.hotelapp_backend.dto.RoomDto;
import com.mitocode.hotelapp_backend.model.Room;
import com.mitocode.hotelapp_backend.service.IRoomService;
import com.mitocode.hotelapp_backend.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@CrossOrigin(originPatterns = "*")
public class RoomController {

    //@Autowired

    private final IRoomService service;
    private final MapperUtil mapper;
    private final MapperUtil mapperUtil;

    //private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RoomDto>> findAll() {

        //List<RoomDto> list = service.findAll().stream().map(this::convertToDto).toList();
        List<RoomDto> list = mapper.mapList(service.findAll(), RoomDto.class);

        return ResponseEntity.ok(list);//controlar la respuesta del servidor a 201 y no solo 200
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody RoomDto dto){
        Room obj = service.save(mapper.map(dto, Room.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRoom()).toUri();
        return ResponseEntity.created(location).build();

    }@GetMapping("/{id}")
    public ResponseEntity<RoomDto> findById(@PathVariable("id") Integer id) {
        Room obj = service.findById(id);

        //return ResponseEntity.ok(modelMapper.map(obj, PatientDTO.class));
        return ResponseEntity.ok(mapper.map(obj, RoomDto.class));

    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable("id") Integer id, @Valid @RequestBody RoomDto dto){
        Room obj = service.update(id, mapper.map(dto, Room.class));

        return ResponseEntity.ok(mapper.map(obj, RoomDto.class));
    }


}
