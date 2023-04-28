package com.sexware.sexware.Repositories;

import com.sexware.sexware.Model.Registrer.PlatoRegister.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlatoRepository extends JpaRepository<Plato, Long> {

    public Plato findByNombre(String nombre);

    @Query(nativeQuery = true,
    value = "SELECT * FROM plato p WHERE p.estado = true")
    public List<Plato> listarPlatosHabilitados();

}
