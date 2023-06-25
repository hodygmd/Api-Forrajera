package com.example.apiforrajera.repositories;

import com.example.apiforrajera.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Integer> {
    @Query("select m from Marca m where m.status=1 order by m.nombre asc ")
    public List<Marca> findAllByStatus();
    @Query("select m.nombre from Marca m where m.status=1")
    public List<String> findNombresByStatus();
}