package com.sexware.sexware.Repositorys;


import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByEmail(String email);


}
