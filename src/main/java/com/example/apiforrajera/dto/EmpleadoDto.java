package com.example.apiforrajera.dto;

import lombok.Data;

@Data
public class EmpleadoDto {
    private final String clave;
    private String nombre;
    private final String username;
    private final String password;
    private Integer id_puesto;
    private Byte status;
}