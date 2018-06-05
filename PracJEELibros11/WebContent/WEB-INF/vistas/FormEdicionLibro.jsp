<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="org.pracjeelibros.model.Libro"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  A partir del ISBN que nos llega instanciamos un libro para alimentar los controles
	del formulario, el cual será editado por el usuario
 -->
<%!static final String ALL_CATEGORIES = "todas";%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Edici&oacute;n de Libros</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="estilos/formato.css">
<link rel="icon" href="img/favicon.ico">
</head>
<body>
	<noscript>
		<p style="color: red; padding: 0.3em">El navegador actual no
			admite JS o lo tiene desactivado. La validación de datos no
			funcionarÃ¡.</p>
	</noscript>

	<%@include file="fragments/menu.html"%>

	<div class="container jumbotron">
		<h1>Edición de libros</h1>

		<c:set var="Libro" value="${requestScope['libro']}" />

		<form id="datos" method="post" action="updateBook.do"
			onsubmit="return validacion()">
			<div class="form-group">
				<label for="isbn">ISBN:</label> <input readonly class="form-control"
					id="isbn" type="text" name="isbn" value="${libro.isbn}">
			</div>
			<div class="form-group">
				<label for="titulo">Titulo:</label> <input class="form-control"
					id="titulo" type="text" name="titulo" value="${libro.titulo}">
			</div>
			<div class="form-group">
				<label for="categoria">Categoria:</label> <select
					class="form-control" id="categoria" name="categoria">
					<option value="0" selected>seleccionar</option>
					<c:forEach var="cat" items="${todas}">
						<c:choose>
							<c:when
								test="${cat eq libro.categoria}">
								<option selected value="${cat.id}">${cat.descripcion}</option>
							</c:when>
							<c:otherwise>
								<option value="${cat.id}">${cat.descripcion}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<input class="btn btn-primary" type="submit" value="Actualizar">

		</form>
		<span id="status"></span>
	</div>
	<%@include file="fragments/pie.html"%>
	<script src="js/validacion.js"></script>
</body>
</html>