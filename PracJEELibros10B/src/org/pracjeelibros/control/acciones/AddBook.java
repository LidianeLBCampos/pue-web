package org.pracjeelibros.control.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBook extends NewOrUpdateAccion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		validar(request);
		
		libro.insertar();

		return "showBooks.do";
	}


}
