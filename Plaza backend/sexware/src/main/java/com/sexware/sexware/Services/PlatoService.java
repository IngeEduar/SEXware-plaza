package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Peticiones.PlatoModEstado;
import com.sexware.sexware.Model.Registrer.PlatoRegister.*;

import java.util.List;

public interface PlatoService {

    public CrearPlatoResponse crearPlato(CrearPlatoRequest crearPlatoRequest) throws Exception;
    public List<Categoria> listarCategorias();
    public Categoria agregarCategoria(AgregarCategoriaRequest categoriaRequest);
    public List<Plato> listarPlatoRest(String nombre);
    public String modificarPlato(ModificarPlatoRequest modificarPlatoRequest) throws Exception;
    public String deshabilitarPlato(PlatoModEstado platoModEstado);


}
