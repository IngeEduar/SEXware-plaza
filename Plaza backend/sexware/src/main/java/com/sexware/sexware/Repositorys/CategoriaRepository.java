package com.sexware.sexware.Repositorys;

import com.sexware.sexware.Model.Registrer.PlatoRegister.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public Categoria findByCategoriaNombre(String nombre);

}
