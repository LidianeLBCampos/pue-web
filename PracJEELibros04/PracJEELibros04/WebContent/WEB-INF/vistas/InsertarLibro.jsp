<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="org.pracjeelibros.Libro"%>
<%@ page import="org.pracjeelibros.DataBaseException"%>

<%
	request.setCharacterEncoding("UTF-8");

	String isbn = request.getParameter("isbn");
	String titulo = request.getParameter("titulo");
	String categoria = request.getParameter("categoria");
	try {
		if (isbn == null || titulo == null || categoria == null || isbn.trim().isEmpty()
				|| titulo.trim().isEmpty() || categoria.equals("seleccionar")) {
			String mensaje = "Se ha intentado dar de alta un libro con datos imcompletos";
			throw new Exception(mensaje);
		} else {
			try {
				Libro libro = new Libro(isbn, titulo, categoria);
				libro.insertar();
				response.sendRedirect("showBooks");
			} catch (DataBaseException e) {
				out.print(e.getMessage());
				out.print(e.getCause().getMessage());
			}
		}
	} catch (Exception e) {
		out.print(e.getMessage());
	}
%>