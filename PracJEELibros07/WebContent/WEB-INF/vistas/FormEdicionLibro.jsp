<%@ page import="java.util.List"%>
<%@ page import="org.pracjeelibros.Libro"%>
<%@ page import="org.pracjeelibros.DataBaseException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
	static final String ALL_CATEGORIES = new String("todas");
	static final String BOOK = new String("libro");
%>
<%
	Libro libro = (Libro) request.getAttribute(BOOK);
	List<String> categorias = (List<String>)request.getAttribute(ALL_CATEGORIES);

%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Edicion de libros</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="estilos/formato.css">
</head>
<body>	

	<noscript>
		<p style="color:red; padding: 0.3em">
			El navegador actual no admite JS o lo tiene desactivado. La validaciÃ³n de datos no funcionarÃ¡.
		</p>	 
	</noscript>
	
	<%@include file="fragments/menu.html" %>

	<div class="container jumbotron">	
		<h1>Edición de libros</h1>	
		
		<form id="datos" method="post" action="updateBook.do" onsubmit="return validacion()">
			<div class="form-group">
				<label for="isbn">ISBN:</label>
				<input readonly class="form-control" id="isbn" type="text" name="isbn"
					value="<%=libro.getIsbn()%>"> 					
			</div>
			<div class="form-group">
				<label for="titulo">Titulo:</label>
				<input class="form-control"id="titulo" type="text" name="titulo"
					value="<%=libro.getTitulo()%>">
			</div>
			<div class="form-group">
				<label for="categoria">Categoria:</label>
						
				<select class="form-control" id="categoria" name="categoria">
					<option value="seleccionar" selected>seleccionar</option>
<%					
					for (String cat : categorias) {
						if (cat.equals(libro.getCategoria())) {										%>
							<option selected value="<%=cat%>"><%=cat%></option>
<%						} else {																	%>
							<option value="<%=cat%>"><%=cat%></option>
<%						}
					}																				%>
				</select>
					
			</div>
			<input class="btn btn-primary" type="submit" value="Actualizar"></p>
				
		</form>		
		<span id="status"></span>
	</div>
	<%@include file="fragments/pie.html" %>
	<script src="js/validacion.js"></script>
</body>
</html>