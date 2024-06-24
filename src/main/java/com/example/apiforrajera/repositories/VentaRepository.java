package com.example.apiforrajera.repositories;

import com.example.apiforrajera.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,String> {
    @Query("select v from Venta v where v.status=1 order by v.clave_empleado.nombre")
    public List<Venta> findAllByStatus();
    @Query("select v from Venta v where v.status=1 and v.clave_empleado.clave=?1")
    public List<Venta> findAllByClave_empleado(String clave);
}
