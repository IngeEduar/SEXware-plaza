package com.sexware.sexware.Model.Registrer.RestaurantRegistrer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "desc_pedido")
public class DetallePedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombrePlato;
    private int cantidad;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id", referencedColumnName = "NUMEROP")
    private Pedidos pedidos;

}
