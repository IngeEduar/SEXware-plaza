package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Registrer.PlatoRegister.*;

import java.util.List;

public interface PlatoService {

    public CrearPlatoResponse crearPlato(CrearPlatoRequest crearPlatoRequest);
    public List<Categoria> listarCategorias();
    public Categoria agregarCategoria(AgregarCategoriaRequest categoriaRequest);
    public List<Plato> listarPlatoRest(String nombreRest);


}
