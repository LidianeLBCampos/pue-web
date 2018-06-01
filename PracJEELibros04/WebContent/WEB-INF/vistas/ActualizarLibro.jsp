<%@page import="org.pracjeelibros.DataBaseException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="org.pracjeelibros.Libro"%>
<%@ page import="org.pracjeelibros.DataBaseException"%>

<%
	request.setCharacterEncoding("UTF-8");

	String isbn = request.getParameter("isbn");
	String titulo = request.getParameter("titulo");	
	String categoria = request.getParameter("categoria");
	
	Libro libro = new Libro(isbn,titulo,categoria);
	
	try{
		libro.actualizar();
		response.sendRedirect("showBooks");
	} catch (DataBaseException e) {
		out.print(e.getMessage());
		out.print(e.getCause().getMessage());
	}
%>