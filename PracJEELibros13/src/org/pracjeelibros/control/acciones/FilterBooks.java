package org.pracjeelibros.control.acciones;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.jpa.CategoriaDaoJpaImpl;
import org.pracjeelibros.dao.jpa.LibroDaoJpaImpl;
import org.pracjeelibros.model.Libro;

public class FilterBooks extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<Libro> librosFiltrados = Collections.emptyList();
		int categoria = Integer.parseInt(request.getParameter("categoria"));
		
		if (categoria > 0) {
			librosFiltrados = new LibroDaoJpaImpl().buscarPorIdCategoria(categoria);
		}
		request.setAttribute("filtrados", librosFiltrados);
		return new ShowBooks().ejecutar(request, response);
	}


}
