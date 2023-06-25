package com.example.apiforrajera.repositories;

import com.example.apiforrajera.entities.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion,Integer> {
    @Query("select p from Presentacion p where p.status=1 order by p.nombre asc ")
    public List<Presentacion> findAllByStatus();
    @Query("select p.nombre from Presentacion p where p.status=1")
    public List<String> findNombresByStatus();
}
