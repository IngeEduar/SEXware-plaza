package com.sexware.sexware.Repositorys;

import com.sexware.sexware.Model.Registrer.PlatoRegister.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatoRepository extends JpaRepository<Plato, Long> {

    public Plato findByNombre(String nombre);

}