package com.sexware.sexware.Model.Registrer.RestaurantRegistrer;

import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "pedido")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numeroP;
    private String estado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empleado_id", referencedColumnName = "ID")
    private Usuario empleadoAsignado;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", referencedColumnName = "ID")
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "NIT")
    private Restaurant restaurant;

}
