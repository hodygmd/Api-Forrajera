package com.example.apiforrajera.repositories;

import com.example.apiforrajera.entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository  extends JpaRepository<DetalleVenta,Integer> {
    @Query("select dv from DetalleVenta dv where dv.folio_cv.folio=?1")
    public DetalleVenta[] findByFolio_v(String folio);
    @Query("select dv from DetalleVenta dv where dv.clave_producto.clave=?1 and dv.folio_cv.folio=?2")
    public DetalleVenta findByClave_producto(String clave,String folio);
}
