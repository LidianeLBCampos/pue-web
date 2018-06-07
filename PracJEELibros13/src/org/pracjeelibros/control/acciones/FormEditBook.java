package org.pracjeelibros.control.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.jpa.CategoriaDaoJpaImpl;
import org.pracjeelibros.dao.jpa.LibroDaoJpaImpl;
import org.pracjeelibros.model.Categoria;
import org.pracjeelibros.model.Libro;

public class FormEditBook extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

		String isbn = request.getParameter("isbn");
		libro =  new LibroDaoJpaImpl().buscarTodos();
		request.setAttribute("libro", libro);

		List<Categoria> categorias = new CategoriaDaoJpaImpl().buscarTodos();
		request.setAttribute("todas", categorias);
		
		return PREFIX + "FormEdicionLibro.jsp";
	}


}
