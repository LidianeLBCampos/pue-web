package org.pracjeelibros.control.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.dao.EntityDao;
import org.pracjeelibros.dao.jpa.EntityDaoJpaImpl;
import org.pracjeelibros.dao.jpa.LibroDaoJpaImpl;
import org.pracjeelibros.model.Libro;

public class DeleteBook extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		Libro libro = new Libro();
		libro.setIsbn(isbn);
		
		@SuppressWarnings("unchecked")
		EntityDao<Libro, String> dao = new LibroDaoJpaImpl();
		dao.borrar(libro);
		
		return "showBooks.do";
	}

}
