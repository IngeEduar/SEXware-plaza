package com.sexware.sexware.Repositorys;


import com.sexware.sexware.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByEmail(String email);


}
