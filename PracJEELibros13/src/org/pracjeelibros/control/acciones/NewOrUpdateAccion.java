package org.pracjeelibros.control.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.EntityDao;
import org.pracjeelibros.model.Libro;

public abstract class NewOrUpdateAccion extends Accion {
	
	protected Libro libro;
	protected EntityDao<Libro, String> dao;

	public void validar(HttpServletRequest request) {
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		int categoria = Integer.parseInt(request.getParameter("categoria"));

		if (isbn == null || titulo == null || isbn.trim().isEmpty() || titulo.trim().isEmpty()
				|| categoria==0) {
			String mensaje = "Se ha intentado dar de alta/actualizar un libro con datos incompletos";
			throw new RuntimeException(mensaje);
		}
		libro = new Libro(isbn, titulo, categoria);
	}

	@Override
	public abstract String ejecutar(HttpServletRequest request, HttpServletResponse response);


}
