package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.UnidadMedidaDto;
import com.example.apiforrajera.entities.UnidadMedida;
import com.example.apiforrajera.services.UnidadMedidaService;
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
@RequestMapping("/unidad-medida")
@Tag(name = "Unidad de Medida", description = "API para unidades de medida")
public class UnidadMedidaController {
    @Autowired
    private UnidadMedidaService service;
    @Operation(summary = "Obtener todas las unidades de medida por estado")
    @ApiResponse(responseCode = "200", description = "Unidades de medida encontradas")
    @GetMapping
    public ResponseEntity<List<UnidadMedida>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @Operation(summary = "Crear una nueva unidad de medida")
    @ApiResponse(responseCode = "201", description = "Unidad de medida creada")
    @PostMapping("/create")
    public ResponseEntity<UnidadMedida> create(@RequestBody UnidadMedidaDto unidadMedidaDto){
        return new ResponseEntity<>(service.create(unidadMedidaDto),HttpStatus.CREATED);
    }
    @Operation(summary = "Actualizar una unidad de medida existente")
    @ApiResponse(responseCode = "200", description = "Unidad de medida actualizada")
    @PutMapping("/update/{id}")
    public ResponseEntity<UnidadMedida> update(@PathVariable("id")Integer id,@RequestBody UnidadMedidaDto unidadMedidaDto){
        return new ResponseEntity<>(service.update(id,unidadMedidaDto),HttpStatus.OK);
    }
    @Operation(summary = "Eliminar una unidad de medida")
    @ApiResponse(responseCode = "200", description = "Unidad de medida eliminada")
    @PutMapping("/delete/{id}")
    public ResponseEntity<UnidadMedida> delete(@PathVariable("id")Integer id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}