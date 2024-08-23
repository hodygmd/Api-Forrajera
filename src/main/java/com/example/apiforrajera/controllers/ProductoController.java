package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.ProductoDto;
import com.example.apiforrajera.entities.Producto;
import com.example.apiforrajera.services.EmpleadoService;
import com.example.apiforrajera.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/producto")
@Tag(name = "Producto", description = "API para productos")
public class ProductoController {
    @Autowired
    private ProductoService service;
    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Obtener todos los productos por estado")
    @ApiResponse(responseCode = "200", description = "Productos encontrados")
    @GetMapping
    public ResponseEntity<List<Producto>> getAllByStatus(@RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponse(responseCode = "201", description = "Producto creado")
    @PostMapping("/create")
    public ResponseEntity<Producto> create(@RequestBody ProductoDto productoDto,
                                           @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.create(productoDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un producto existente")
    @ApiResponse(responseCode = "200", description = "Producto actualizado")
    @PutMapping("/update/{clave}")
    public ResponseEntity<Producto> update(@PathVariable("clave")String clave,@RequestBody ProductoDto productoDto,
                                           @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.update(clave,productoDto),HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un producto")
    @ApiResponse(responseCode = "200", description = "Producto eliminado")
    @PutMapping("/delete/{clave}")
    public ResponseEntity<Producto> delete(@PathVariable("clave")String clave,
                                           @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.delete(clave),HttpStatus.OK);
    }
}
