package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.MrcFncnDto;
import com.example.apiforrajera.entities.Funcion;
import com.example.apiforrajera.services.EmpleadoService;
import com.example.apiforrajera.services.FuncionService;
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
@RequestMapping("/funcion")
@Tag(name = "Función", description = "API para funciones")
public class FuncionController {
    @Autowired
    private FuncionService service;
    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Obtener todas las funciones por estado")
    @ApiResponse(responseCode = "200", description = "Funciones encontradas")
    @GetMapping
    public ResponseEntity<List<Funcion>> getAllByStatus(@RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }

    @Operation(summary = "Crear una nueva función")
    @ApiResponse(responseCode = "201", description = "Función creada")
    @PostMapping("/create")
    public ResponseEntity<Funcion> create(@RequestBody MrcFncnDto mrcFncnDto,
                                          @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.create(mrcFncnDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una función existente")
    @ApiResponse(responseCode = "200", description = "Función actualizada")
    @PutMapping("/update/{id}")
    public ResponseEntity<Funcion> update(@PathVariable("id")Integer id,@RequestBody MrcFncnDto mrcFncnDto,
                                          @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.update(id,mrcFncnDto),HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una función")
    @ApiResponse(responseCode = "200", description = "Función eliminada")
    @PutMapping("/delete/{id}")
    public ResponseEntity<Funcion> delete(@PathVariable("id")Integer id,
                                          @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}