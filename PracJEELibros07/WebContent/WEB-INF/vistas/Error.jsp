
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isErrorPage="true"%>

<%	String titulo = exception.getMessage();
	String detalle = exception.getCause().getMessage();
	System.out.println(titulo + "/n" + detalle);
	%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Error</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style>
	</style>
</head>
<body>
	<div class="container jumbotron">
	<h1 class="container display-4 text-danger">Error</h1>
		<div class='alert alert-danger' role='alert'>
			<b><%=titulo %></b>
			<details>
				<summary>información técnica</summary>
				<p><%=detalle %></p>
			</details>
		</div>
	</div>
	<%@include file="fragments/pie.html" %>
	
	<script>

	</script>
</body>
</html>