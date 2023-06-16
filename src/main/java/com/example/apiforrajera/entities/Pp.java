package com.example.apiforrajera.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "proveedor_producto")
public class Pp {
    @Id
    @ManyToOne
    @JoinColumn(name = "clave_proveedor")
    private Proveedor clave_proveedor;
    @Id
    @ManyToOne
    @JoinColumn(name = "clave_producto")
    private Producto clave_producto;
}
