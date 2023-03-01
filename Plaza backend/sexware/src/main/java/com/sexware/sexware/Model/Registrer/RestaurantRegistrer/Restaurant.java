package com.sexware.sexware.Model.Registrer.RestaurantRegistrer;

import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "restaurantes")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 50,nullable = false)
    private String nombre;
    @Column(unique = true, length = 100, nullable = false)
    private String nit;
    @Column(nullable = false)
    private String direccion;
    @Column(length = 13,nullable = false)
    private String telefono;
    @Column(length = 50,nullable = false)
    private String urlLogo;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Usuario usuarioId;

}
