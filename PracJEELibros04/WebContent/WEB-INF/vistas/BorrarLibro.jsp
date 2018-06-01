<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.pracjeelibros.Libro"%>
<%@ page import="org.pracjeelibros.DataBaseException"%>
<%
	String isbn = request.getParameter("isbn");
	Libro libro = new Libro();
	libro.setIsbn(isbn);
	
	try {
		libro.borrar();
		response.sendRedirect("showBooks");
	} catch (Exception e) {
		out.print(e.getMessage());
		out.print(e.getCause().getMessage());
	}
%>