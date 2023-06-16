package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.PuestoDto;
import com.example.apiforrajera.entities.Puesto;
import com.example.apiforrajera.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/puesto")
public class PuestoController {
    @Autowired
    private PuestoService service;
    @GetMapping
    public ResponseEntity<List<Puesto>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Puesto> create(@RequestBody PuestoDto puestoDto){
        return new ResponseEntity<>(service.create(puestoDto),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Puesto> update(@PathVariable("id")Integer id,@RequestBody PuestoDto puestoDto){
        return new ResponseEntity<>(service.update(id,puestoDto),HttpStatus.OK);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Puesto> delete(@PathVariable("id")Integer id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}
