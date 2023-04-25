package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.ConfigUser.OrdenarRestaurant;
import com.sexware.sexware.Model.Peticiones.RegisterClienteRequest;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositorys.RestaurantRepository;
import com.sexware.sexware.Repositorys.RolRepository;
import com.sexware.sexware.Repositorys.UsuarioRepository;
import com.sexware.sexware.Security.Exceptions.MyException;
import com.sexware.sexware.Services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
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

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        restaurantList.sort(new OrdenarRestaurant());

        return restaurantList;
    }


}
