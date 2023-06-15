package com.example.apiforrajera.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @Column(name = "clave")
    private String clave;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private float precio;
    @Column(name = "stock_min")
    private Integer stock_min;
    @Column(name = "stock_max")
    private Integer stock_max;
    @Column(name = "existencias")
    private Integer existencias;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria id_categoria;
    @ManyToOne
    @JoinColumn(name = "id_presentacion")
    private Presentacion id_presentacion;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca id_marca;
    @Column(name = "status")
    private byte status;
}
