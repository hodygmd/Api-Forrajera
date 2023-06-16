package com.example.apiforrajera.dto;

import lombok.Data;

@Data
public class ProveedorDto {
    private String clave;
    private String razon_social;
    private String telefono;
    private String direccion;
    private String contacto;
    private Byte status;
}