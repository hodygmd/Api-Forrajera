package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.CategoriaDto;
import com.example.apiforrajera.entities.Categoria;
import com.example.apiforrajera.services.CategoriaService;
import com.example.apiforrajera.services.EmpleadoService;
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
@RequestMapping("/categoria")
@Tag(name = "Categoria", description = "API para categorías")
public class CategoriaController {
    @Autowired
    private CategoriaService service;
    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Obtener todas las categorías por estado")
    @ApiResponse(responseCode = "200", description = "Categorías encontradas")
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllByStatus(@RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }

    @Operation(summary = "Crear una nueva categoría")
    @ApiResponse(responseCode = "201", description = "Categoría creada")
    @PostMapping("/create")
    public ResponseEntity<Categoria> create(@RequestBody CategoriaDto categoriaDto,
                                            @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.create(categoriaDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una categoría existente")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada")
    @PutMapping("/update/{id}")
    public ResponseEntity<Categoria> update(@PathVariable("id")Integer id,
                                            @RequestBody CategoriaDto categoriaDto,
                                            @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.update(id,categoriaDto),HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría eliminada")
    @PutMapping("/delete/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable("id")Integer id,
                                            @RequestParam(value = "token") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}