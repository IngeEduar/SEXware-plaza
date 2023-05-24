package com.sexware.sexware.Repositories;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedidos, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM pedido p WHERE p.restaurant_id= :nit AND p.estado= :estado ")
    public List<Pedidos> listarPedidosRest(String nit, String estado);

    public Pedidos findByNumeroP (int numeroP);

}
