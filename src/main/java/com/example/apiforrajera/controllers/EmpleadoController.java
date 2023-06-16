package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.EmpleadoDto;
import com.example.apiforrajera.entities.Empleado;
import com.example.apiforrajera.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService service;
    @GetMapping
    public ResponseEntity<List<Empleado>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Empleado> create(@RequestBody EmpleadoDto empleadoDto){
        return new ResponseEntity<>(service.create(empleadoDto),HttpStatus.CREATED);
    }
    @PutMapping("/update/{clave}")
    public ResponseEntity<Empleado> update(@PathVariable("clave")String clave,@RequestBody EmpleadoDto empleadoDto){
        return new ResponseEntity<>(service.update(clave,empleadoDto),HttpStatus.OK);
    }
    @PutMapping("/delete/{clave}")
    public ResponseEntity<Empleado> delete(@PathVariable("clave")String clave){
        return new ResponseEntity<>(service.delete(clave),HttpStatus.OK);
    }
}
