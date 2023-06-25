package com.example.apiforrajera.repositories;

import com.example.apiforrajera.entities.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion,Integer> {
    @Query("select f from Funcion f where f.status=1 order by f.nombre asc ")
    public List<Funcion> findAllByStatus();
    @Query("select f.nombre from Funcion f where f.status=1")
    public List<String> findNombresByStatus();
}
