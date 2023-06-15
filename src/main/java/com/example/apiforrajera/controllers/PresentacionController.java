package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.PresentacionDto;
import com.example.apiforrajera.entities.Presentacion;
import com.example.apiforrajera.services.PresentacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/presentacion")
public class PresentacionController {
    @Autowired
    private PresentacionService service;
    @GetMapping
    public ResponseEntity<List<Presentacion>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Presentacion> create(@RequestBody PresentacionDto presentacionDto){
        return new ResponseEntity<>(service.create(presentacionDto),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Presentacion> update(@PathVariable("id")Integer id,@RequestBody PresentacionDto presentacionDto){
        return new ResponseEntity<>(service.update(id,presentacionDto),HttpStatus.OK);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Presentacion> delete(@PathVariable("id")Integer id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}
