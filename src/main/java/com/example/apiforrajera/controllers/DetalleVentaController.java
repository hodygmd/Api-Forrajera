package com.example.apiforrajera.controllers;

import com.example.apiforrajera.dto.DcvDto;
import com.example.apiforrajera.entities.DetalleVenta;
import com.example.apiforrajera.services.DetalleVentaService;
import com.example.apiforrajera.services.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/dv")
@Tag(name = "Detalle de Venta", description = "API para detalles de venta")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService service;
    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Crear detalles de venta")
    @ApiResponse(responseCode = "201", description = "Detalles de venta creados")
    @PostMapping("/create")
    public ResponseEntity<DetalleVenta[]> create(@RequestBody DcvDto[] dto,
                                                 @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los detalles de venta por folio")
    @ApiResponse(responseCode = "200", description = "Detalles de venta encontrados")
    @GetMapping("/{folio}")
    public ResponseEntity<DetalleVenta[]> getAllByFolio(@PathVariable("folio")String folio,
                                                        @RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        return new ResponseEntity<>(service.getAllByFolio(folio),HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un detalle de venta")
    @ApiResponse(responseCode = "200", description = "Detalle de venta eliminado")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Integer id,@RequestHeader(value = "Authorization") String token){
        empleadoService.validate(token);
        service.delete(id);
    }
}
