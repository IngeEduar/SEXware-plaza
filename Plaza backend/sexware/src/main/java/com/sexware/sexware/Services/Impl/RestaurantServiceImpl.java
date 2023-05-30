package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Peticiones.EntregarPedidoRequest;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.DetallePedido;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Pedidos;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Model.Respuestas.ListarPedidosResponse;
import com.sexware.sexware.Repositories.AuditoriaRepository;
import com.sexware.sexware.Repositories.DetallePedidoRepository;
import com.sexware.sexware.Repositories.PedidoRepository;
import com.sexware.sexware.Repositories.RestaurantRepository;
import com.sexware.sexware.Security.Exceptions.MyException;
import com.sexware.sexware.Services.RestaurantService;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RestaurantServiceImpl implements RestaurantService {


    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuditoriaRepository auditoriaRepository;


    @Override
    public Restaurant guardarRestaurant(RestaurantRequest restaurantRequest, String admin){
        List<Usuario> usuarioList = usuarioService.listarUsuario();
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        for (Restaurant rest : restaurantList){
            if (restaurantRequest.getNombre().equals(rest.getNombre())){
                throw new MyException("Ya existe un restaurante con este nombre");
            }
            if (restaurantRequest.getNit().equals(rest.getNit())){
                throw new MyException("Ya hay un restaurante resgistrado con este NIT");
            }
        }

        Usuario userA = null;
        for (Usuario user: usuarioList){
            if (user.getRoles().getRolNombre().equals("ADMIN")&&
                    Objects.equals(user.getEmail(), admin)){
                userA = user;
            }
        }

        if (userA == null){
            throw new MyException("No eres Admin");
        }

        String emailUser = restaurantRequest.getUser();
        Usuario userP = null;
        for (Usuario user:usuarioList){
            if (user.getRoles().getRolNombre().equals("PROPIETARIO")&&
                    Objects.equals(user.getEmail(), emailUser)){
                userP = user;
            }
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setNombre(restaurantRequest.getNombre());
        restaurant.setDireccion(restaurantRequest.getDireccion());
        restaurant.setNit(restaurantRequest.getNit());
        restaurant.setTelefono(restaurantRequest.getTelefono());
        restaurant.setUrlLogo(restaurantRequest.getUrlLogo());
        restaurant.setUsuarioId(userP);

        restaurantRepository.save(restaurant);

        String fecha = String.valueOf(LocalDate.now());
        String hora = String.valueOf(LocalTime.now());

        Auditoria auditoria = new Auditoria();
        auditoria.setUsuario(userA);
        auditoria.setTitulo("REGISTRO");
        assert userP != null;
        auditoria.setDescripcion("Registro el restaurante "+restaurant.getNombre()+
                " Propietario: "+userP.getNombre()+" "+userP.getApellido()+
                " Documento: "+userP.getCedula());
        auditoria.setFecha(fecha+" "+hora);

        auditoriaRepository.save(auditoria);

        return restaurant;
    }

    @Override
    public Restaurant obtenerRestaurante(String nombre) {
        return restaurantRepository.findByNombre(nombre);
    }

    @Override
    public List<Restaurant> listarRestaurante() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> listaRestaurantPropietario(String correo) {


        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<Restaurant> restaurantList = new ArrayList<>();

        for(Restaurant rest:restaurants){
            if (Objects.equals(rest.getUsuarioId().getEmail(), correo)){
                restaurantList.add(rest);
            }
        }

        return restaurantList;
    }

    @Override
    public String eliminarRestaurant(Long id) {

        try{
            restaurantRepository.deleteById(id);
            return "Restaurante eliminado correctamente";
        }catch (Exception e){
            e.printStackTrace();
            return "Error al eliminar el restaurante";
        }



    }

    @Override
    public Restaurant actualizarRestaurante(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<ListarPedidosResponse> listarPedidosDelRest(String nombreRest, String estado) {

        Restaurant restaurant = restaurantRepository.findByNombre(nombreRest);

        List<Pedidos> pedidosList = pedidoRepository.listarPedidosRest(restaurant.getNit(), estado);

        List<ListarPedidosResponse> pedidosResponses = new ArrayList<>();

        for (Pedidos pedidos : pedidosList){
                List<DetallePedido> detallePedidos = detallePedidoRepository.listarDetallePedido(pedidos.getNumeroP());

                ListarPedidosResponse pedidosResponse = new ListarPedidosResponse();
                pedidosResponse.setNumeroP(pedidos.getNumeroP());
                pedidosResponse.setEstado(pedidos.getEstado());
                pedidosResponse.setNombreCliente(pedidos.getUsuario().getNombre());
                pedidosResponse.setDetallePedidos(detallePedidos);

                pedidosResponses.add(pedidosResponse);
        }

        return pedidosResponses;
    }

    @Override
    public ListarPedidosResponse asignarmePedido(int numeroP, String email) {

        Usuario usuario = usuarioService.obtenerUsuario(email);
        Pedidos pedidos = pedidoRepository.findByNumeroP(numeroP);

        if (!Objects.equals(pedidos.getEstado(), "PENDIENTE")){
            throw new MyException("EL pedido ya esta: "+pedidos.getEstado());
        }

        pedidos.setEmpleadoAsignado(usuario);
        pedidos.setEstado("EN PREPARACION");

        pedidoRepository.save(pedidos);

        List<DetallePedido> detallePedidos = detallePedidoRepository.listarDetallePedido(pedidos.getNumeroP());

        ListarPedidosResponse pedidosResponse = new ListarPedidosResponse();
        pedidosResponse.setNumeroP(pedidos.getNumeroP());
        pedidosResponse.setEstado(pedidos.getEstado());
        pedidosResponse.setNombreCliente(pedidos.getUsuario().getNombre());
        pedidosResponse.setDetallePedidos(detallePedidos);

        return pedidosResponse;
    }

    @Override
    public List<ListarPedidosResponse> listarPedidosEmpleado(String email, String estado) {

        Usuario user = usuarioService.obtenerUsuario(email);
        List<Pedidos> pedidosList = pedidoRepository.pedidosAsigEmpleado(user.getId(), estado);

        List<ListarPedidosResponse> pedidosResponses = new ArrayList<>();

        for (Pedidos pedidos : pedidosList){
                List<DetallePedido> detallePedidos = detallePedidoRepository.listarDetallePedido(pedidos.getNumeroP());

                ListarPedidosResponse pedidosResponse = new ListarPedidosResponse();
                pedidosResponse.setNumeroP(pedidos.getNumeroP());
                pedidosResponse.setEstado(pedidos.getEstado());
                pedidosResponse.setNombreCliente(pedidos.getUsuario().getNombre());
                pedidosResponse.setDetallePedidos(detallePedidos);

                pedidosResponses.add(pedidosResponse);
        }

        return pedidosResponses;
    }

    @Override
    public List<ListarPedidosResponse> listarPedidosCliente(String email) {

        Usuario user = usuarioService.obtenerUsuario(email);
        List<Pedidos> pedidosList = pedidoRepository.findByUsuario(user);

        return ordenarListaPedidos(pedidosList);
    }

    @Override
    public void entregarPedido(EntregarPedidoRequest pedidoRequest) {

        Pedidos pedido = pedidoRepository.findByNumeroP(pedidoRequest.getNumeroP());

        if (pedido == null){
            throw new MyException("No se encuentra el pedido");
        }
        if (!Objects.equals(pedido.getEstado(), "LISTO")){
            throw new MyException("El pedido no esta listo");
        }

        if (!Objects.equals(pedido.getCodigo(), pedidoRequest.getCodigo())){
            throw new MyException("El PIN es incorrecto");
        }

        pedido.setEstado("ENTREGADO");
        pedido.setCodigo("");

        pedidoRepository.save(pedido);

    }

    public List<ListarPedidosResponse> ordenarListaPedidos(List<Pedidos> pedidosList){
        List<ListarPedidosResponse> pedidosResponses = new ArrayList<>();

        for (Pedidos pedidos : pedidosList){
            if (Objects.equals(pedidos.getEstado(), "EN PREPARACION") ||
                    Objects.equals(pedidos.getEstado(), "PENDIENTE")){
                List<DetallePedido> detallePedidos = detallePedidoRepository.listarDetallePedido(pedidos.getNumeroP());

                ListarPedidosResponse pedidosResponse = new ListarPedidosResponse();
                pedidosResponse.setNumeroP(pedidos.getNumeroP());
                pedidosResponse.setEstado(pedidos.getEstado());
                pedidosResponse.setNombreCliente(pedidos.getUsuario().getNombre());
                pedidosResponse.setDetallePedidos(detallePedidos);

                pedidosResponses.add(pedidosResponse);
            }
        }

        return pedidosResponses;
    }
}
