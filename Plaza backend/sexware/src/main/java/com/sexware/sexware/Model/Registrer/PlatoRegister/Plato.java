package com.sexware.sexware.Model.Registrer.PlatoRegister;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "plato")
public class Plato implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false, length = 10)
    private String precio;
    @Column(nullable = false)
    private String img;
    private boolean estado = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_restaurante")
    private Restaurant restaurant;


}
