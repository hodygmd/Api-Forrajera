package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.PresentacionDto;
import com.example.apiforrajera.entities.Presentacion;
import com.example.apiforrajera.services.EmpleadoService;
import com.example.apiforrajera.services.PresentacionService;
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
@RequestMapping("/presentacion")
@Tag(name = "Presentación", description = "API para presentaciones")
public class PresentacionController {
    @Autowired
    private PresentacionService service;
    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Obtener todas las presentaciones por estado")
    @ApiResponse(responseCode = "200", description = "Presentaciones encontradas")
    @GetMapping
    public ResponseEntity<List<Presentacion>> getAllByStatus(@RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }

    @Operation(summary = "Crear una nueva presentación")
    @ApiResponse(responseCode = "201", description = "Presentación creada")
    @PostMapping("/create")
    public ResponseEntity<Presentacion> create(@RequestBody PresentacionDto presentacionDto,
                                               @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.create(presentacionDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una presentación existente")
    @ApiResponse(responseCode = "200", description = "Presentación actualizada")
    @PutMapping("/update/{id}")
    public ResponseEntity<Presentacion> update(@PathVariable("id")Integer id,@RequestBody PresentacionDto presentacionDto,
                                               @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.update(id,presentacionDto),HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una presentación")
    @ApiResponse(responseCode = "200", description = "Presentación eliminada")
    @PutMapping("/delete/{id}")
    public ResponseEntity<Presentacion> delete(@PathVariable("id")Integer id,
                                               @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}
