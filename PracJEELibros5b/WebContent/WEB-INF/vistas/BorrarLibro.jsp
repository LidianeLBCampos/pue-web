<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.pracjeelibros.Libro"%>
<%
	String isbn = request.getParameter("isbn");
	Libro libro = new Libro();
	libro.setIsbn(isbn);
	
	libro.borrar();
	response.sendRedirect("showBooks");
%>