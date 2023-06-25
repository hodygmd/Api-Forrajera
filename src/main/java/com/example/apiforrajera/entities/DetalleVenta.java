package com.example.apiforrajera.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "folio_cv")
    private Venta folio_cv;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clave_producto")
    private Producto clave_producto;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio")
    private Float precio;
}
