<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.List"%>
<%@ page import="org.pracjeelibros.Libro"%>

<%
	String isbn = request.getParameter("isbn");
	Libro libro = Libro.buscarPorClave(isbn);
%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Edición de libro</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="estilos/formato.css">
</head>
<body>	
	<noscript>
		<p style="color:red; padding: 0.3em">
			El navegador actual no admite JS o lo tiene desactivado. La validacion de datos no funcionarÃ¡.
		</p>	 
	</noscript>
	
	<%@include file="fragments/menu.html" %>
	
	<div class="container jumbotron">	
		<h1>Edicion de libros</h1>	
		
		<form id="datos" method="post" action="updateBook" onsubmit="return validacion()">
			<div class="form-group">
				<label for="isbn">ISBN:</label>
				<input readonly class="form-control" id="isbn" type="text" name="isbn"
					value="<%=libro.getIsbn()%>"> 					
			</div>
			<div class="form-group">
				<label for="titulo">Titulo:</label>
				<input class="form-control" id="titulo" type="text" name="titulo"
					value="<%=libro.getTitulo()%>">
			</div>
			<div class="form-group">
				<label for="categoria">Categoria:</label>
						
				<select class="form-control" id="categoria" name="categoria">
					<option value="seleccionar" selected>seleccionar</option>
					<%
					List<String> listaCat = Libro.buscarTodasLasCategorias();
					for (String cat : listaCat) {
						if (cat.equals(libro.getCategoria())) {
					%>
						<option selected value="<%=cat%>"><%=cat%></option>
					<%
						} else {
							%>
							<option value="<%=cat%>"><%=cat%></option>
							<%
						}
					}
					%>
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