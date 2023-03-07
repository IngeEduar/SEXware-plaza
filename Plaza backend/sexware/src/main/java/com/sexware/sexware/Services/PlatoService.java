package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Registrer.PlatoRegister.AgregarCategoriaRequest;
import com.sexware.sexware.Model.Registrer.PlatoRegister.Categoria;
import com.sexware.sexware.Model.Registrer.PlatoRegister.CrearPlatoRequest;
import com.sexware.sexware.Model.Registrer.PlatoRegister.CrearPlatoResponse;

import java.util.List;

public interface PlatoService {

    public CrearPlatoResponse crearPlato(CrearPlatoRequest crearPlatoRequest);
    public List<Categoria> listarCategorias();
    public Categoria agregarCategoria(AgregarCategoriaRequest categoriaRequest);


}
