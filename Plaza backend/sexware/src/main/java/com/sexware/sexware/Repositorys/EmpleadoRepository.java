package com.sexware.sexware.Repositorys;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.UsuarioEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<UsuarioEmpleado, Long> {
    public List<UsuarioEmpleado> findByRestaurant(Restaurant restaurant);
}
