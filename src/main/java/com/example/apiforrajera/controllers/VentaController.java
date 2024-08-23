package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.CvDto;
import com.example.apiforrajera.entities.Venta;
import com.example.apiforrajera.services.EmpleadoService;
import com.example.apiforrajera.services.VentaService;
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
@RequestMapping("/venta")
@Tag(name = "Venta", description = "API para ventas")
public class VentaController {
    @Autowired
    private VentaService service;
    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Obtener todas las ventas por estado")
    @ApiResponse(responseCode = "200", description = "Ventas encontradas")
    @GetMapping
    public ResponseEntity<List<Venta>> getAllByStatus(@RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las ventas por estado")
    @ApiResponse(responseCode = "200", description = "Ventas encontradas")
    @GetMapping("/ver-ventas/{clave}")
    public ResponseEntity<List<Venta>> getAllByStatusAndEmpleado(@PathVariable("clave")String clave,
                                                                 @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.getAllByStatusAndEmpleado(clave), HttpStatus.OK);
    }

    @Operation(summary = "Crear una nueva venta")
    @ApiResponse(responseCode = "201", description = "Venta creada")
    @PostMapping("/create")
    public ResponseEntity<Venta> create(@RequestBody CvDto ventaDto,
                                        @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.create(ventaDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una venta existente")
    @ApiResponse(responseCode = "200", description = "Venta actualizada")
    @PutMapping("/delete/{folio}")
    public ResponseEntity<Venta> delete(@PathVariable("folio")String folio,
                                        @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.delete(folio),HttpStatus.OK);
    }
}
