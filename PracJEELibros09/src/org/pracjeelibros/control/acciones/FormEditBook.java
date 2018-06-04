package org.pracjeelibros.control.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.Libro;

public class FormEditBook extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

		String isbn = request.getParameter("isbn");
		Libro libro = Libro.buscarPorClave(isbn);
		request.setAttribute("libro", libro);

		List<String> categorias = Libro.buscarTodasLasCategorias();
		request.setAttribute("todas", categorias);
		
		return PREFIX + "FormEdicionLibro.jsp";
	}


}
