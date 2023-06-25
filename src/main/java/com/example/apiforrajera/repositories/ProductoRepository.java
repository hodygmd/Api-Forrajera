package com.example.apiforrajera.repositories;

import com.example.apiforrajera.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,String> {
    @Query("select p from Producto p where p.status=1 order by p.nombre asc ")
    public List<Producto> findAllByStatus();
    @Query("select p.nombre from Producto p where p.status=1")
    public List<String> findNombresByStatus();
}
