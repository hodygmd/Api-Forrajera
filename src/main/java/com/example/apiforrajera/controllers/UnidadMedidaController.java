package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.UnidadMedidaDto;
import com.example.apiforrajera.entities.UnidadMedida;
import com.example.apiforrajera.services.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/unidad-medida")
public class UnidadMedidaController {
    @Autowired
    private UnidadMedidaService service;
    @GetMapping
    public ResponseEntity<List<UnidadMedida>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<UnidadMedida> create(@RequestBody UnidadMedidaDto unidadMedidaDto){
        return new ResponseEntity<>(service.create(unidadMedidaDto),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UnidadMedida> update(@PathVariable("id")Integer id,@RequestBody UnidadMedidaDto unidadMedidaDto){
        return new ResponseEntity<>(service.update(id,unidadMedidaDto),HttpStatus.OK);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<UnidadMedida> delete(@PathVariable("id")Integer id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}