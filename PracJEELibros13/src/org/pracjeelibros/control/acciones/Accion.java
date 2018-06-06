package org.pracjeelibros.control.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public abstract class Accion {
	protected static final String PREFIX = "/WEB-INF/vistas/";
	private static Logger log = Logger.getLogger(Accion.class);

	private static String getNombreClaseAccion(String operacion) {
		String primeChar = operacion.substring(1, 2).toUpperCase();
		int limite = operacion.indexOf(".");
		operacion = primeChar.concat(operacion.substring(2,limite));
		return getNombrePaquete() + "." + operacion;
	}

	private static String getNombrePaquete() {
		return Accion.class.getPackage().getName();
	}

	public static Accion getAccion(String operacion) {
		String nombreClaseAccion = getNombreClaseAccion(operacion);
		Accion accion = null;
		try {
			accion = (Accion) Class.forName(nombreClaseAccion).newInstance();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return accion;
	}

	public abstract String ejecutar(HttpServletRequest request, HttpServletResponse response);
}
