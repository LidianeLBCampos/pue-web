package org.pracjeelibros.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.control.acciones.Accion;

@WebServlet("*.do")
public class ControladorLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String NEXT_PAGE = null;

	public ControladorLibros() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String operacion = request.getServletPath();
		Accion accion = Accion.getAccion(operacion);
		NEXT_PAGE = accion.ejecutar(request, response);
		request.getRequestDispatcher(NEXT_PAGE).forward(request, response);
	}
}
