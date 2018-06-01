<!--
Esta JSP se encarga de mostrar una lista de todos los libros que tenemos
en la base de datos
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="org.pracjeelibros.Libro"%>

<%!
	static final String ALLCATEGORIES = new String("todas");
	static final String FILTERBOOKS = new String("filtrados");
	static final String SHOWBOOKS = new String("showBooks");
	static final String ALLBOOKS = new String("todos");
%>
<%
	String categoria = request.getParameter("fcategoria");
	List<String> categorias = (List<String>)request.getAttribute(ALLCATEGORIES);
	List<Libro> librosFiltrados = (List<Libro>) request.getAttribute(FILTERBOOKS);
	List<Libro> libros = (List<Libro>) request.getAttribute(ALLBOOKS);
	
	libros = librosFiltrados.isEmpty() ? libros : librosFiltrados;	
%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Lista de Libros</title>
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
	</style>
</head>
<body>
	<div class="container jumbotron">
	<h1 class="container display-4">Listado de libros</h1>
	<%@include file="fragments/menu.html" %>	

		<select onchange="refrescar('showBooks.do',this)" class="form-control" name="fcategoria">
			<option value="seleccionar">todas las categorías</option>
			
<%			for (String cat : categorias) {
				String selected = "";
				if ( categoria != null && !categoria.isEmpty() && categoria.equals(cat) ) {
					selected = "selected";
				}																				%>
				<option <%=selected%> value="<%=cat%>"><%=cat%></option>
<%			}																					%>
		</select>
	<form>
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

		for (Libro libro : libros) {															%>
			<tr>
				<td><%=libro.getIsbn()%></td>
				<td><%=libro.getTitulo()%></td>
				<td><%=libro.getCategoria()%></td>
				<td align="center">
					<a href="formUpdateBook.do?isbn=<%=libro.getIsbn()%>">
						<img class="si-glyph" src="svg/si-glyph-edit.svg"/>
					</a> 
					<a onclick="confirmarEliminacion(<%=libro.getIsbn()%>)" >
						<img class="si-glyph" src="svg/si-glyph-trash.svg"/>
					</a>
				</td>
			</tr>
<%			}																							%>
		
	</tbody>
	</table>

	<a href="formAddBook.do" class="badge badge-primary">Insertar Libro</a>
	</div>
	<%@include file="fragments/pie.html" %>
	
	<script>
		function confirmarEliminacion(isbn) {
			var resultado = confirm("seguro que quieres borrar " + isbn + "?");
			if (resultado) {
				window.location.href = "deleteBook.do?isbn=" + isbn;
			}
		}
		
		function refrescar(destino,objeto) {
			window.location.href=destino+"?fcategoria="+objeto.value;
		}
		
		function mostrarOcultar(objeto) {}
	</script>
</body>
</html>