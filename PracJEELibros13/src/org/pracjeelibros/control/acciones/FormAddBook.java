package org.pracjeelibros.control.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.jpa.CategoriaDaoJpaImpl;
import org.pracjeelibros.model.Categoria;
import org.pracjeelibros.model.Libro;

public class FormAddBook extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<Categoria> categorias =  new CategoriaDaoJpaImpl().buscarTodos();
		request.setAttribute("todas", categorias);
		
		return PREFIX + "FormAltaLibro.jsp";
	}


}
