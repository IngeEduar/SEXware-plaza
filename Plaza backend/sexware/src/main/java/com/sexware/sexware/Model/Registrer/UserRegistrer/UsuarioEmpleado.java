package com.sexware.sexware.Model.Registrer.UserRegistrer;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "empleado")
public class UsuarioEmpleado extends Usuario{

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
