package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Peticiones.EntregarPedidoRequest;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;
import com.sexware.sexware.Model.Respuestas.ListarPedidosResponse;

import java.util.List;

public interface RestaurantService {

    public Restaurant guardarRestaurant(RestaurantRequest restaurantRequest, String admin) throws Exception;

    public Restaurant obtenerRestaurante(String nombre);
    public List<Restaurant> listarRestaurante();
    public List<Restaurant> listaRestaurantPropietario(String correo);
    public String eliminarRestaurant (Long id);

    public Restaurant actualizarRestaurante(Restaurant restaurant);
    public List<ListarPedidosResponse> listarPedidosDelRest (String nombreRest, String estado);
    public ListarPedidosResponse asignarmePedido (int numeroP, String email);
    public List<ListarPedidosResponse> listarPedidosEmpleado(String email, String estado);
    public List<ListarPedidosResponse> listarPedidosCliente(String email);
    public void entregarPedido (EntregarPedidoRequest pedidoRequest);

}
