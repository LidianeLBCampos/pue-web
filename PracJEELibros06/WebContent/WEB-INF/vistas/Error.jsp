<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<link rel="icon" href="img/favicon.ico">
<title>Error</title>
<style>
body {
	background-color: black;
	color: yellow;
	}
	
	p{
	font-size: 1.2em;
	font-family: courier;
		}
	
	a{
		padding: 0.5em 1em;
		background-color: orange;
		border: 1px dashed yellow;
		color: black;
		border-radius:5px;
		text-decoration: none;
		display: inline-block;
		
		}
</style>
</head>
<body>
Ha ocurrido un error en la aplicacion :<%=exception.getMessage()%>
<img alt="imagen" src="img/dino.png">
<a href="showBooks">Ir a la visualización de libros</a>
<br>
<details>
	<summary>Información técnica</summary>
	<p>Información del suporte: <%=exception.getCause().getMessage()%></p>
</details>
</body>
</html>