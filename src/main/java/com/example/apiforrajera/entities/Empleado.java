package com.example.apiforrajera.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @Column(name = "clave")
    private String clave;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne
    @JoinColumn(name = "id_puesto")
    private Puesto id_puesto;
    @Column(name = "status")
    private Byte status;
}
