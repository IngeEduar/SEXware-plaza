package com.sexware.sexware.Repositorys;


import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByEmail(String email);
    public Usuario findByTokenPassword(String token);
    public List<Usuario> findByRestaurant(String restaurant);



}
