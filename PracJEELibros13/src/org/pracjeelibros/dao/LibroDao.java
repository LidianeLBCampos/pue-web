package org.pracjeelibros.dao;

import java.util.List;

import org.pracjeelibros.model.Categoria;
import org.pracjeelibros.model.Libro;

public interface LibroDao {
	List<Libro> buscarPorIdCategoria(int categoria);

}
