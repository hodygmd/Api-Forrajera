package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.PuestoDto;
import com.example.apiforrajera.entities.Puesto;
import com.example.apiforrajera.services.EmpleadoService;
import com.example.apiforrajera.services.PuestoService;
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
@RequestMapping("/puesto")
@Tag(name = "Puesto", description = "API para puestos")
public class PuestoController {
    @Autowired
    private PuestoService service;
    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Obtener todos los puestos por estado")
    @ApiResponse(responseCode = "200", description = "Puestos encontrados")
    @GetMapping
    public ResponseEntity<List<Puesto>> getAllByStatus(@RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo puesto")
    @ApiResponse(responseCode = "201", description = "Puesto creado")
    @PostMapping("/create")
    public ResponseEntity<Puesto> create(@RequestBody PuestoDto puestoDto,
                                         @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.create(puestoDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un puesto existente")
    @ApiResponse(responseCode = "200", description = "Puesto actualizado")
    @PutMapping("/update/{id}")
    public ResponseEntity<Puesto> update(@PathVariable("id")Integer id,@RequestBody PuestoDto puestoDto,
                                         @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.update(id,puestoDto),HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un puesto")
    @ApiResponse(responseCode = "200", description = "Puesto eliminado")
    @PutMapping("/delete/{id}")
    public ResponseEntity<Puesto> delete(@PathVariable("id")Integer id,
                                         @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}
