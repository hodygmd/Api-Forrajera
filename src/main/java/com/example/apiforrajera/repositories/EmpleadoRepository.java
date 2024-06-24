package com.example.apiforrajera.repositories;

import com.example.apiforrajera.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,String> {
    Empleado findEmpleadoByUsername(String username);
    Optional<Empleado> findByUsername(String username);
    @Query("select e from Empleado e where e.status=1 order by e.nombre asc ")
    public List<Empleado> findAllByStatus();
    @Query("select e.nombre from Empleado e where e.status=1")
    public List<String> findNombresByStatus();
    Empleado findEmpleadoByClave(String clave);
}