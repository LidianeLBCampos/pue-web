package org.pracjeelibros.control.acciones;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.Libro;

public class FilterBooks extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<Libro> librosFiltrados = Collections.emptyList();
		String categoria = request.getParameter("categoria");
		
		if (categoria != null && !categoria.isEmpty() && !categoria.equals("seleccionar")) {
			librosFiltrados = Libro.buscarPorCategoria(categoria);
		}
		request.setAttribute("filtrados", librosFiltrados);
		return new ShowBooks().ejecutar(request, response);
	}


}
