package com.sexware.sexware.Repositories;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM desc_pedido d WHERE d.pedido_id= :numeroP")
    public List<DetallePedido> listarDetallePedido (int numeroP);
}
