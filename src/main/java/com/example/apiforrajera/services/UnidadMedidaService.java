package com.example.apiforrajera.services;

import com.example.apiforrajera.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaService {
    @Autowired
    private UnidadMedidaRepository repository;
}
