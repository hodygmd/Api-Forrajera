package com.example.apiforrajera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/")
public class Controller {
    @GetMapping
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("API Forrajera", HttpStatus.OK);
    }
}
