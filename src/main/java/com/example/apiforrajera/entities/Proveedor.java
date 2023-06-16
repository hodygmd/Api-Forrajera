package com.example.apiforrajera.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @Column(name = "clave")
    private String clave;
    @Column(name = "razon_social")
    private String razon_social;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "contacto")
    private String contacto;
    @Column(name = "status")
    private Byte status;
}
