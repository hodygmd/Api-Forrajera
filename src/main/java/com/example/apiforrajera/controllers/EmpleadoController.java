package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.EmpleadoDto;
import com.example.apiforrajera.dto.TokenDto;
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
    public ResponseEntity<List<Empleado>> getAllByStatus(@RequestHeader(value = "Authorization") String token){
        service.validate(token);
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo empleado")
    @ApiResponse(responseCode = "201", description = "Empleado creado")
    @PostMapping("/create")
    public ResponseEntity<Empleado> create(@RequestBody EmpleadoDto empleadoDto,
                                           @RequestHeader(value = "Authorization") String token){
        service.validate(token);
        return new ResponseEntity<>(service.create(empleadoDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un empleado existente")
    @ApiResponse(responseCode = "200", description = "Empleado actualizado")
    @PutMapping("/update/{clave}")
    public ResponseEntity<Empleado> update(@PathVariable("clave")String clave,@RequestBody EmpleadoDto empleadoDto,
                                           @RequestHeader(value = "Authorization") String token){
        service.validate(token);
        return new ResponseEntity<>(service.update(clave,empleadoDto),HttpStatus.OK);
    }

    @Operation(summary = "Actualizar la contraseña de un empleado existente")
    @ApiResponse(responseCode = "200", description = "Contraseña actualizada")
    @PutMapping("/update-pass/{clave}")
    public ResponseEntity<Empleado> updatePassword(@PathVariable("clave")String clave,@RequestBody EmpleadoDto empleadoDto,
                                                   @RequestHeader(value = "Authorization") String token){
        service.validate(token);
        return new ResponseEntity<>(service.updatePassword(clave,empleadoDto),HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un empleado")
    @ApiResponse(responseCode = "200", description = "Empleado eliminado")
    @PutMapping("/delete/{clave}")
    public ResponseEntity<Empleado> delete(@PathVariable("clave")String clave,@RequestHeader(value = "Authorization") String token){
        service.validate(token);
        return new ResponseEntity<>(service.delete(clave),HttpStatus.OK);
    }

    @Operation(summary = "Iniciar sesión de un empleado")
    @ApiResponse(responseCode = "201", description = "Empleado logueado")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody EmpleadoDto empleadoDto,@RequestHeader(value = "Authorization") String token) {
        service.validate(token);
        return new ResponseEntity<>(service.login(empleadoDto.getUsername(),empleadoDto.getPassword()),HttpStatus.OK);
    }

    @Operation(summary = "Obtener un token")
    @ApiResponse(responseCode = "200", description = "Token creado")
    @GetMapping("/token")
    public ResponseEntity<TokenDto> token(@RequestParam(value = "clave") String clave, @RequestParam(value = "pass") String pass){
        TokenDto token=service.createToken(clave,pass);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Validar un token")
    @ApiResponse(responseCode = "201", description = "Token validado")
    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestHeader(value = "Authorization") String token){
        TokenDto tokenDto=service.validate(token);
        return ResponseEntity.ok(tokenDto);
    }
}