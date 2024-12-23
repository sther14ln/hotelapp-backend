package com.mitocode.hotelapp_backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Integer idRoom;

    @NotNull
    @Size(min = 3, message = "{number.size}")
    private String number;

    @NotNull
    @Size(min = 3, message = "{type.size}")
    private String type;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Boolean available;
}
