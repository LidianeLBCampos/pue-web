package org.pracjeelibros.control.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.LibroDao;

public class UpdateBook extends NewOrUpdateAccion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		validar(request);
		
		LibroDao libroDao = new LibroDao();
		libroDao.actualizar(libro);
		
		return "showBooks.do";

	}

}
