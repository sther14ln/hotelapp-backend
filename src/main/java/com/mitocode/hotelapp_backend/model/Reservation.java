package com.mitocode.hotelapp_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) ///hace comparacion las referencias
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include// solo comparara por el id
    private Integer idReservation;

    @Column(nullable = false, length = 70)
    private String customerName;

    @Column(nullable = false)
    private LocalDate checkinDate;

    @Column(nullable = false)
    private LocalDate checkoutDate;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false, foreignKey = @ForeignKey(name = "FK_RESERVATION_ROOM"))
    private Room room;
}
