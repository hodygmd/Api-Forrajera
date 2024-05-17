package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.MrcFncnDto;
import com.example.apiforrajera.entities.Marca;
import com.example.apiforrajera.services.MarcaService;
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
@RequestMapping("/marca")
@Tag(name = "Marca", description = "API para marcas")
public class MarcaController {
    @Autowired
    private MarcaService service;
    @Operation(summary = "Obtener todas las marcas por estado")
    @ApiResponse(responseCode = "200", description = "Marcas encontradas")
    @GetMapping
    public ResponseEntity<List<Marca>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @Operation(summary = "Crear una nueva marca")
    @ApiResponse(responseCode = "201", description = "Marca creada")
    @PostMapping("/create")
    public ResponseEntity<Marca> create(@RequestBody MrcFncnDto mrcFncnDto){
        return new ResponseEntity<>(service.create(mrcFncnDto),HttpStatus.CREATED);
    }
    @Operation(summary = "Actualizar una marca existente")
    @ApiResponse(responseCode = "200", description = "Marca actualizada")
    @PutMapping("/update/{id}")
    public ResponseEntity<Marca> update(@PathVariable("id")Integer id,@RequestBody MrcFncnDto mrcFncnDto){
        return new ResponseEntity<>(service.update(id,mrcFncnDto),HttpStatus.OK);
    }
    @Operation(summary = "Eliminar una marca")
    @ApiResponse(responseCode = "200", description = "Marca eliminada")
    @PutMapping("/delete/{id}")
    public ResponseEntity<Marca> delete(@PathVariable("id")Integer id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}