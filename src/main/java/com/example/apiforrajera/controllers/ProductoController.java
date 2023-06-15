package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.ProductoDto;
import com.example.apiforrajera.entities.Producto;
import com.example.apiforrajera.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService service;
    @GetMapping
    public ResponseEntity<List<Producto>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Producto> create(@RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(service.create(productoDto),HttpStatus.CREATED);
    }
    @PutMapping("/update/{clave}")
    public ResponseEntity<Producto> update(@PathVariable("clave")String clave,@RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(service.update(clave,productoDto),HttpStatus.OK);
    }
    @PutMapping("/delete/{clave}")
    public ResponseEntity<Producto> delete(@PathVariable("clave")String clave){
        return new ResponseEntity<>(service.delete(clave),HttpStatus.OK);
    }
}
