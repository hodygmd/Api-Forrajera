package com.example.apiforrajera.repositories;

import com.example.apiforrajera.entities.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida,Integer> {
    @Query("select um from UnidadMedida um where um.status=1 order by um.unidad asc ")
    public List<UnidadMedida> findAllByStatus();
    @Query("select um.unidad from UnidadMedida um where um.status=1")
    public List<String> findNombresByStatus();
}