package org.pracjeelibros.control.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.jpa.EntityDaoJpaImpl;
import org.pracjeelibros.dao.jpa.LibroDaoJpaImpl;

public class AddBook extends NewOrUpdateAccion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		validar(request);
		
		dao= new LibroDaoJpaImpl();
		dao.insertar(libro);

		return "showBooks.do";
	}


}
