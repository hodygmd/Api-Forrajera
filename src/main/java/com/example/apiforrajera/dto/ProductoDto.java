package com.example.apiforrajera.dto;

import lombok.Data;

@Data
public class ProductoDto {
    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer stock_min;
    private Integer stock_max;
    private Integer existencias;
    private Integer id_categoria;
    private Integer id_presentacion;
    private Integer id_marca;
    private Byte status;
}
