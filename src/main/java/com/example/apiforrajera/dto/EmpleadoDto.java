package com.example.apiforrajera.dto;

import lombok.Data;

@Data
public class EmpleadoDto {
    private String clave;
    private String nombre;
    private String username;
    private String password;
    private Integer id_puesto;
    private Byte status;
}