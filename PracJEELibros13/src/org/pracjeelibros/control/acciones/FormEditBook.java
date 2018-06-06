package org.pracjeelibros.control.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.CategoriaDao;
import org.pracjeelibros.dao.LibroDao;
import org.pracjeelibros.model.Categoria;
import org.pracjeelibros.model.Libro;

public class FormEditBook extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

		String isbn = request.getParameter("isbn");
		Libro libro = new LibroDao().buscarPorClave(isbn);
		request.setAttribute("libro", libro);

		List<Categoria> categorias = new CategoriaDao().buscarTodas();
		request.setAttribute("todas", categorias);
		
		return PREFIX + "FormEdicionLibro.jsp";
	}


}
