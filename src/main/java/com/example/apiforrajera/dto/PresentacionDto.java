package com.example.apiforrajera.dto;

import lombok.Data;

@Data
public class PresentacionDto {
    private String nombre;
    private String descripcion;
    private Integer id_unidad_medida;
    private Float medida;
    private Byte status;
}
