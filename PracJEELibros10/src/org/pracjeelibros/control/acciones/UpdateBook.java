package org.pracjeelibros.control.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBook extends NewOrUpdateAccion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		validar(request);
		libro.actualizar();
		return "showBooks.do";

	}

}
