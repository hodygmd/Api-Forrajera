package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.MrcFncnDto;
import com.example.apiforrajera.entities.Marca;
import com.example.apiforrajera.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/marca")
public class MarcaController {
    @Autowired
    private MarcaService service;
    @GetMapping
    public ResponseEntity<List<Marca>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Marca> create(@RequestBody MrcFncnDto mrcFncnDto){
        return new ResponseEntity<>(service.create(mrcFncnDto),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Marca> update(@PathVariable("id")Integer id,@RequestBody MrcFncnDto mrcFncnDto){
        return new ResponseEntity<>(service.update(id,mrcFncnDto),HttpStatus.OK);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Marca> delete(@PathVariable("id")Integer id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}