<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="org.pracjeelibros.Libro"%>

<% 
	String catRecibida = request.getParameter("categoria"); 
%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Lista de Libros</title>
	<link rel="icon" href="img/favicon.ico">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style>
		.si-glyph {
			color: red;
			width: 16px;
			height: 16px;
		}
		th {
			text-align: center;
		}
		
		.borrar {
			cursor: pointer;
		}
	</style>
</head>
<body>
	<div class="container jumbotron">
	<h1 class="container display-4">Listado de libros</h1>
	<%@include file="fragments/menu.html" %>	
	
	<select onchange="refrescar('showBooks', this)" class="form-control" name="categoria">
		<option value="seleccionar">todas las categorías</option>
		<%
			List<String> categorias = Libro.buscarTodasLasCategorias();
			for (String cat : categorias) {
				if (catRecibida != null && cat.equals(catRecibida)) {
		%>
		<option selected value="<%=cat%>"><%=cat%></option>
			<% } else { %>
		<option value="<%=cat%>"><%=cat%></option>
		<%
			   }		
			}	
		%>
	</select>
	
	<hr>
	
	<table class="table table-striped table-bordered table-hover table-sm">
	<colgroup>
		<col style="width:15%">
		<col style="width:50%">
		<col style="width:20%">
		<col style="width:15%">
	</colgroup>
  	<thead>
	    <tr>
	      <th scope="col">ISBN</th>
	      <th scope="col">Título</th>
	      <th scope="col">Categoría</th>
	      <th scope="col">Operaciones</th>	      
	    </tr>
  	</thead>
	<tbody>
	<%		
		List<Libro> libros = null;	
		if (catRecibida != null && !catRecibida.equals("seleccionar")) 
			libros = Libro.buscarPorCategoria(catRecibida);
		else
			libros = Libro.buscarTodos();
		
		for (Libro libro : libros) {			
	%>
		<tr>
			<td><%=libro.getIsbn()%></td>
			<td><%=libro.getTitulo()%></td>
			<td><%=libro.getCategoria()%></td>
			<td align="center">
				<a href="editBook?isbn=<%=libro.getIsbn()%>">
					<img class="si-glyph" src="svg/si-glyph-edit.svg"/>
				</a> 
				
				<a class="borrar" onclick="confirmarEliminacion('<%=libro.getIsbn()%>')">
					<img class="si-glyph" src="svg/si-glyph-trash.svg"/>
				</a>
				
			</td>
		</tr>			
	<%
		}
	%>
	</tbody>
	</table>
	
	<a href="formAddBook" class="badge badge-primary">Insertar Libro</a>
	</div>
	<%@include file="fragments/pie.html" %>
	
	<script>
		function confirmarEliminacion(isbn) {
			var destino = "deleteBook?isbn=" + isbn
			var resultado = 
				confirm("¿Está seguro de eliminar este libro (ISBN: '"+ isbn +"')?")
			if (resultado)
				 window.location.href = destino
		}
		
		function refrescar(pagina, id) {
			window.location.href=pagina+"?categoria=" + id.value
		}
	</script>
	
</body>
</html>