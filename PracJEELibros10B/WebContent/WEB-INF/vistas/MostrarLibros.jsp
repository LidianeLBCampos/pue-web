<!--
Esta JSP se encarga de mostrar una lista de todos los libros que tenemos
en la base de datos
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="org.pracjeelibros.model.Libro"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Lista de Libros</title>
<link rel="icon" href="img/favicon.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
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
		<%@include file="fragments/menu.html"%>

		<!-- 
		Llenamos un combo con las categorias para que el usuario
		pueda filtrar por alguna de ellas 
	-->
		

		<c:set var="catRecibida" value="${param.categoria}"/>

		<select onchange="refrescar('filterBooks.do', this)"
			class="form-control" name="categoria">
			<option value="seleccionar">todas las categorías</option>

			<c:forEach var="cat" items="${todas}">
				<c:choose>
					<c:when test="${(!empty filtrados) && (cat eq catRecibida)}">
						<option selected value="${cat}">${cat}</option>
					</c:when>
					<c:otherwise>
						<option value="${cat}">${cat}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>

		</select>

		<hr>

		<!-- 
		TODO:
		Mostramos una tabla con los libros de la base de datos. Si el
		usuario ha seleccionado una categoría sólo se muestran los libros
		de esa categoria 
	-->

		<table class="table table-striped table-bordered table-hover table-sm">
			<colgroup>
				<col style="width: 15%">
				<col style="width: 50%">
				<col style="width: 20%">
				<col style="width: 15%">
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

				<c:choose>
					<c:when test="${empty filtrados}">
						<c:set var="libros" value="${todos}"/>
					</c:when>
					<c:otherwise>
						<c:set var="libros" value="${filtrados}"/>
					</c:otherwise>
				</c:choose>
				
				<c:forEach var="libro" items="${libros}">
					<tr>
						<td>${libro.isbn}</td>
						<td>${libro.titulo}</td>
						<td>${libro.categoria}</td>
						<td align="center"><a
							href="formEditBook.do?isbn=${libro.isbn}"> <img
								class="si-glyph" src="svg/si-glyph-edit.svg" />
						</a> <a class="borrar"
							onclick="confirmarEliminacion('${libro.isbn}')"> <img
								class="si-glyph" src="svg/si-glyph-trash.svg" />
						</a></td>
					</tr>
				</c:forEach>
								
			</tbody>
		</table>

		<a href="formAddBook.do" class="badge badge-primary">Insertar
			Libro</a>
	</div>
	<%@include file="fragments/pie.html"%>

	<script>
		function confirmarEliminacion(isbn) {
			var destino = "deleteBook.do?isbn=" + isbn
			var resultado = confirm("¿Está seguro de eliminar este libro (ISBN: '"
					+ isbn + "')?")
			if (resultado)
				window.location.href = destino
		}

		function refrescar(pagina, id) {
			window.location.href = pagina + "?categoria=" + id.value
		}
	</script>

</body>
</html>