package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.CvDto;
import com.example.apiforrajera.entities.Venta;
import com.example.apiforrajera.entities.Venta2;
import com.example.apiforrajera.repositories.EmpleadoRepository;
import com.example.apiforrajera.repositories.VentaRepository;
import com.example.apiforrajera.repositories.VentaRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository repository;
    @Autowired
    private VentaRepository2 repository2;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    public List<Venta> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public List<Venta> getAllByStatusAndEmpleado(String clave){
        return repository.findAllByClave_empleado(clave);
    }
    public Venta create(CvDto ventaDto){
        Venta venta=new Venta();
        Venta2 venta2=new Venta2();
        return getVenta(ventaDto,venta,venta2);
    }
    public Venta delete(String folio){
        Venta venta=repository.getReferenceById(folio);
        Venta2 venta2=repository2.getReferenceById(folio);
        venta.setStatus((byte)0);
        venta2.setStatus((byte)0);
        repository2.save(venta2);
        return repository.save(venta);
    }
    private Venta getVenta(CvDto ventaDto,Venta venta,Venta2 venta2){
        venta2.setFolio(ventaDto.getFolio());
        venta2.setFecha(LocalDateTime.now());
        venta2.setTotal(ventaDto.getTotal());
        venta2.setClave_empleado(empleadoRepository.findById(ventaDto.getClave_empleado()).get());
        venta2.setStatus(ventaDto.getStatus());
        repository2.save(venta2);

        venta.setFolio(ventaDto.getFolio());
        venta.setFecha(LocalDateTime.now());
        venta.setTotal(ventaDto.getTotal());
        venta.setClave_empleado(empleadoRepository.findById(ventaDto.getClave_empleado()).get());
        venta.setStatus(ventaDto.getStatus());
        return repository.save(venta);
    }
}
