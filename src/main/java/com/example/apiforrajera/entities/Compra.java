package com.example.apiforrajera.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @Column(name = "folio")
    private String folio;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "total")
    private Float total;
    @ManyToOne
    @JoinColumn(name = "clave_empleado")
    private Empleado clave_empleado;
    @Column(name = "status")
    private Byte status;
}
