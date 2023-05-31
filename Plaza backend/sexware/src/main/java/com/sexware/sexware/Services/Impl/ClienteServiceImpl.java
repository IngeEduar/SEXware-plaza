package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Peticiones.RealizarPedidoRequest;
import com.sexware.sexware.Model.Peticiones.RegisterClienteRequest;
import com.sexware.sexware.Model.Registrer.PlatoRegister.Categoria;
import com.sexware.sexware.Model.Registrer.PlatoRegister.Plato;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.DetallePedido;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Pedidos;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositories.*;
import com.sexware.sexware.Security.Exceptions.MyException;
import com.sexware.sexware.Services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PlatoRepository platoRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public void registrarCliente(RegisterClienteRequest clienteRequest) {
        List<Usuario> usuarioList = usuarioRepository.findAll();

        for (Usuario usuario : usuarioList){

            if (Objects.equals(usuario.getEmail(), clienteRequest.getEmail())&&
                  Objects.equals(usuario.getRoles().getRolNombre(), "CLIENTE")){

                throw new MyException("Ya esta registrado un cliente con este correo");
            }

        }

        Rol rol = new Rol();
        rol.setId(4L);
        rol.setRolNombre("CLIENTE");

        rolRepository.save(rol);

        Usuario usuario = modelMapper.map(clienteRequest, Usuario.class);
        usuario.setRoles(rol);
        usuario.setPassword(passwordEncoder.encode(clienteRequest.getPassword()));

        usuarioRepository.save(usuario);

    }

    @Override
    public List<Restaurant> listarRestaurantes() {

        return restaurantRepository.restaurantesOrdenados();

    }

    @Override
    public List<Plato> listarPlatoRest(String nombre) {

        Restaurant restaurant = restaurantRepository.findByNombre(nombre);

        if (restaurant == null){
            throw new MyException("El restaurante no existe");
        }

        List<Plato> platosRest = new ArrayList<>();
        List<Plato> platosHabilitados = platoRepository.listarPlatosHabilitados();


        for (Plato plato : platosHabilitados){

            if (Objects.equals(plato.getRestaurant().getNombre(),nombre)){
                platosRest.add(plato);
            }

        }

        return platosRest;
    }

    @Override
    public List<Plato> listarPlatoCategoria(String nombre, String categoria) {

        Restaurant restaurant = restaurantRepository.findByNombre(nombre);

        if (restaurant == null){
            throw new MyException("El restaurante no existe");
        }

        Categoria categoria1 = categoriaRepository.findByCategoriaNombre(categoria);
        List<Plato> platoList = platoRepository.listarPlatoCategoria(categoria1.getId());
        List<Plato> platos = new ArrayList<>();

        for (Plato plato: platoList) {
            if(Objects.equals(plato.getRestaurant().getNombre(),nombre)){
                platos.add(plato);
            }
        }

        return platos;
    }

    @Override
    public String realizarPedido(String nombreRest, String email, RealizarPedidoRequest[] pedidoRequests) {

        List<Pedidos> pedidosList = pedidoRepository.findAll();
        Pedidos pedido = null;

        for (Pedidos pedi : pedidosList){
            if (Objects.equals(pedi.getUsuario().getEmail(), email) && !Objects.equals(pedi.getEstado(), "ENTREGADO")){
                pedido = pedi;
            }
            if (Objects.equals(pedi.getUsuario().getEmail(), email) && !Objects.equals(pedi.getEstado(), "CANCELADO")){
                pedido = pedi;
            }
        }

        if (pedido != null){
            throw new MyException("Ya realizaste un pedido. ");
        }


        Usuario user = usuarioRepository.findByEmail(email);
        Restaurant restaurant = restaurantRepository.findByNombre(nombreRest);

        int numeroP = pedidosList.size() + 1;

        Pedidos pedidos = new Pedidos();
        pedidos.setNumeroP(numeroP);
        pedidos.setEstado("PENDIENTE");
        pedidos.setUsuario(user);
        pedidos.setRestaurant(restaurant);
        pedidos.setEmpleadoAsignado(null);

        pedidoRepository.save(pedidos);


        for (RealizarPedidoRequest pedido1 : pedidoRequests){
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setNombrePlato(pedido1.getNombrePlato());
            detallePedido.setCantidad(pedido1.getCantidad());
            detallePedido.setPedidos(pedidos);

            detallePedidoRepository.save(detallePedido);
        }


        return "Pedido realizado con exito!";
    }

    @Override
    public void cancelarPedido(int numeroP) {
        Pedidos pedido = pedidoRepository.findByNumeroP(numeroP);

        if (pedido == null){
            throw new MyException("No se encuentra el pedido");
        }
        if (!Objects.equals(pedido.getEstado(),"PENDIENTE")){
            throw new MyException("Lo sentimos, tu pedido ya esta en preparacion y no puede cancelarse");
        }

        pedido.setEstado("CANCELADO");
        pedidoRepository.save(pedido);

    }


}
