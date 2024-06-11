package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.EmpleadoDto;
import com.example.apiforrajera.entities.Empleado;
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
@RequestMapping("/empleado")
@Tag(name = "Empleado", description = "API para empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService service;
    @Operation(summary = "Obtener todos los empleados por estado")
    @ApiResponse(responseCode = "200", description = "Empleados encontrados")
    @GetMapping
    public ResponseEntity<List<Empleado>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @Operation(summary = "Crear un nuevo empleado")
    @ApiResponse(responseCode = "201", description = "Empleado creado")
    @PostMapping("/create")
    public ResponseEntity<Empleado> create(@RequestBody EmpleadoDto empleadoDto){
        return new ResponseEntity<>(service.create(empleadoDto),HttpStatus.CREATED);
    }
    @Operation(summary = "Actualizar un empleado existente")
    @ApiResponse(responseCode = "200", description = "Empleado actualizado")
    @PutMapping("/update/{clave}")
    public ResponseEntity<Empleado> update(@PathVariable("clave")String clave,@RequestBody EmpleadoDto empleadoDto){
        return new ResponseEntity<>(service.update(clave,empleadoDto),HttpStatus.OK);
    }
    @PutMapping("/update-pass/{clave}")
    public ResponseEntity<Empleado> updatePassword(@PathVariable("clave")String clave,@RequestBody EmpleadoDto empleadoDto){
        return new ResponseEntity<>(service.updatePassword(clave,empleadoDto),HttpStatus.OK);
    }
    @Operation(summary = "Eliminar un empleado")
    @ApiResponse(responseCode = "200", description = "Empleado eliminado")
    @PutMapping("/delete/{clave}")
    public ResponseEntity<Empleado> delete(@PathVariable("clave")String clave){
        return new ResponseEntity<>(service.delete(clave),HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody EmpleadoDto empleadoDto) {
        return new ResponseEntity<>(service.login(empleadoDto.getUsername(),empleadoDto.getPassword()),HttpStatus.OK);
    }
}
