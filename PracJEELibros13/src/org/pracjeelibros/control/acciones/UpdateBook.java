package org.pracjeelibros.control.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.EntityDao;
import org.pracjeelibros.dao.jpa.EntityDaoJpaImpl;
import org.pracjeelibros.dao.jpa.LibroDaoJpaImpl;
import org.pracjeelibros.model.Libro;

public class UpdateBook extends NewOrUpdateAccion {

	@SuppressWarnings("unchecked")
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		validar(request);
		
		EntityDao<Libro, String> dao  = new LibroDaoJpaImpl();
		dao.actualizar(libro);
		
		return "showBooks.do";

	}

}
