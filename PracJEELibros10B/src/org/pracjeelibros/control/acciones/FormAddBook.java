package org.pracjeelibros.control.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.model.Libro;

public class FormAddBook extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<String> categorias = Libro.buscarTodasLasCategorias();
		request.setAttribute("todas", categorias);
		
		return PREFIX + "FormAltaLibro.jsp";
	}


}
