<%@page import="org.pracjeelibros.DataBaseException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="org.pracjeelibros.Libro"%>

<%
	request.setCharacterEncoding("UTF-8");

	String isbn = request.getParameter("isbn");
	String titulo = request.getParameter("titulo");	
	String categoria = request.getParameter("categoria");
	
	Libro libro = new Libro(isbn,titulo,categoria);
	libro.actualizar();
	
	response.sendRedirect("showBooks");
%>