package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Peticiones.PlatoModEstado;
import com.sexware.sexware.Model.Registrer.PlatoRegister.*;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositories.*;
import com.sexware.sexware.Security.Exceptions.MyException;
import com.sexware.sexware.Services.PlatoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CrearPlatoImpl implements PlatoService {

    @Autowired
    private PlatoRepository platoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuditoriaRepository auditoriaRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CrearPlatoResponse crearPlato(CrearPlatoRequest crearPlatoRequest) {



            Categoria categoria = categoriaRepository.findByCategoriaNombre(crearPlatoRequest.getCategoria());
            List<Usuario> userList = usuarioRepository.findAll();
            Restaurant restaurant = restaurantRepository.findByNombre(crearPlatoRequest.getRestaurante());
            Usuario users = null;
            for (Usuario user: userList){
                if (user.getRoles().getRolNombre().equals("PROPIETARIO")){
                    if (Objects.equals(restaurant.getUsuarioId().getEmail(),crearPlatoRequest.getLogeado() )){
                        users = user;
                    }
                }
            }

            if (users == null){
                throw new MyException("No eres el propietario");
            }

            Plato plato = modelMapper.map(crearPlatoRequest, Plato.class);
            plato.setRestaurant(restaurant);

            if(categoria == null){
                Categoria categoria2 = new Categoria();
                categoria2.setCategoriaNombre(crearPlatoRequest.getCategoria());
                System.out.println(crearPlatoRequest.getCategoria());

                categoria2 = categoriaRepository.save(categoria2);

                plato.setCategoria(categoria2);
            }else {
                plato.setCategoria(categoria);
            }

            Plato plato1 = platoRepository.save(plato);

            CrearPlatoResponse crearPlatoResponse = modelMapper.map(plato1, CrearPlatoResponse.class);
            crearPlatoResponse.setCategoria(plato1.getCategoria().getCategoriaNombre());

            agregarPlatoAuditoria(crearPlatoRequest.getLogeado(),crearPlatoRequest.getNombre(),
                    "Se registro el plato: ","REGISTRO");


            return crearPlatoResponse;

    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria agregarCategoria(AgregarCategoriaRequest categoriaRequest) {

        Categoria categoria = new Categoria();
        categoria.setCategoriaNombre(categoriaRequest.getNombre());

        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Plato> listarPlatoRest(String nombre) {

        List<Plato> platoList = new ArrayList<>();
        List<Plato> platos = platoRepository.findAll();

        for (Plato plato: platos) {
            if(Objects.equals(plato.getRestaurant().getNombre(),nombre)){
                platoList.add(plato);
            }
        }


        return platoList;
    }

    @Override
    public String modificarPlato(ModificarPlatoRequest modificarPlatoRequest) throws Exception {

            List<Usuario> usuarioList = usuarioRepository.findAll();
            Restaurant restaurant = restaurantRepository.findByNombre(modificarPlatoRequest.getRestaurante());
            Usuario userP = null;
        for (Usuario user: usuarioList){
            if (user.getRoles().getRolNombre().equals("PROPIETARIO")){
                if (Objects.equals(restaurant.getUsuarioId().getEmail(),modificarPlatoRequest.getLogeado() )){
                    userP = user;
                }
            }
        }
        if (userP == null){
            throw new MyException("No eres el propietario");
        }

            Plato plato = platoRepository.findByNombre(modificarPlatoRequest.getNombre());

            plato.setPrecio(modificarPlatoRequest.getPrecio());
            plato.setDescripcion(modificarPlatoRequest.getDescripcion());

            platoRepository.save(plato);
            agregarPlatoAuditoria(modificarPlatoRequest.getLogeado(),modificarPlatoRequest.getNombre(),
                    "Se edito el plato: ","MODIFICO");

            return "Plato editado con exito";



    }

    @Override
    public String deshabilitarPlato(PlatoModEstado platoModEstado) {

        List<Plato> platoList = platoRepository.findAll();
        List<Usuario> usuarioList = usuarioRepository.findAll();

        Usuario user = null;

        for (Usuario usuario: usuarioList){
            if (Objects.equals(usuario.getEmail(), platoModEstado.getUserLogeado()) &&
            Objects.equals(usuario.getRoles().getRolNombre(), "PROPIETARIO")){
                user = usuario;
            }
        }

        if (user == null){
            throw new MyException("El usuario no es propietario");
        }


        Plato plato = null;

        for (Plato plato1 : platoList){
            if (Objects.equals(plato1.getNombre(), platoModEstado.getNombrePlato()) &&
                    Objects.equals(plato1.getRestaurant().getNombre(), platoModEstado.getNombreRest())){
                plato = plato1;
            }
        }

        if (plato == null){
            throw new MyException("Este plato no esta registrado en este restaurante");
        }

        plato.setEstado(platoModEstado.isEstado());
        platoRepository.save(plato);


        return "Se cambio el estado del plato";
    }

    public void agregarPlatoAuditoria(String email,String nombrePlato,String mensaje,String titulo){

        List<Usuario> usuario = usuarioRepository.findAll();
        Usuario user = null;
        for (Usuario users:usuario){
            if (users.getRoles().getRolNombre().equals("PROPIETARIO")&&
                    Objects.equals(users.getEmail(), email)){
                user = users;
            }
        }

        String fecha = String.valueOf(LocalDate.now());
        String hora = String.valueOf(LocalTime.now());

        Auditoria auditoria = new Auditoria();

        auditoria.setTitulo(titulo);
        auditoria.setDescripcion(mensaje+ nombrePlato);
        auditoria.setFecha(fecha+" "+hora);
        auditoria.setUsuario(user);

        auditoriaRepository.save(auditoria);

    }

}
