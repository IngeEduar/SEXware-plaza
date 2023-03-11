package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Registrer.PlatoRegister.*;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositorys.*;
import com.sexware.sexware.Services.PlatoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

        try {

            Categoria categoria = categoriaRepository.findByCategoriaNombre(crearPlatoRequest.getCategoria());

            Restaurant restaurant = restaurantRepository.findByNombre(crearPlatoRequest.getRestaurante());

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

            agregarPlatoAuditoria(crearPlatoRequest.getLogeado(),crearPlatoRequest.getNombre());


            return crearPlatoResponse;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
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
    public String modificarPlato(ModificarPlatoRequest modificarPlatoRequest) {
        try{

            Plato plato = platoRepository.findByNombre(modificarPlatoRequest.getNombre());

            plato.setPrecio(modificarPlatoRequest.getPrecio());
            plato.setDescripcion(modificarPlatoRequest.getDescripcion());

            platoRepository.save(plato);

            return "Plato editado con exito";

        }catch (Exception e){
            e.printStackTrace();
            return "Error al editar el plato";
        }

    }

    public void agregarPlatoAuditoria(String email,String nombrePlato){

        Usuario usuario = usuarioRepository.findByEmail(email);
        String fecha = String.valueOf(LocalDate.now());
        String hora = String.valueOf(LocalTime.now());

        Auditoria auditoria = new Auditoria();

        auditoria.setTitulo("REGISTRO");
        auditoria.setDescripcion("Se Registro el plato: "+ nombrePlato);
        auditoria.setFecha(fecha+" "+hora);
        auditoria.setUsuario(usuario);

        auditoriaRepository.save(auditoria);

    }

}
