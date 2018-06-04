<%@ page isErrorPage="true"%> 
<!DOCTYPE html">
<html lang="es">
	<head>
	<meta charset=UTF-8">
	<title>Error</title>
	<link rel="icon" href="img/favicon.ico">
	<style>
		body {
			background-color: black;
			color: yellow;
		}
		
		p {
			font-size: 1.25em;
			font-family: courier;
		}
		
		a {
			display: inline-block;
			margin: 2em 0;
			padding: 0.5em 1em;
			background-color: orange;
			border: 1px dashed yellow;
			color: black;
			border-radius: 5px;
			font-weight: bold;
			text-decoration: none;
		}
	</style>
	</head>
	<body>
	<pre>	
		
   ____                                                  _   _ 
  / __ \                                                | | | |
 | |  | | ___   ___   ___   ___   ___   ___  _ __  ___  | | | |
 | |  | |/ _ \ / _ \ / _ \ / _ \ / _ \ / _ \| '_ \/ __| | | | |
 | |__| | (_) | (_) | (_) | (_) | (_) | (_) | |_) \__ \ |_| |_|
  \____/ \___/ \___/ \___/ \___/ \___/ \___/| .__/|___/ (_) (_)
                                            | |                
                                            |_|                
	
	</pre>
	
		<p>Ha ocurrido un error en la aplicacion :<%=exception.getMessage()%></p>
		<img src="img/dino.jpg" alt="Imagen dinosaurio">
		<br>
		<a href="showBooks.do">IR a la visualización de libros</a>
		<details>
			<summary>Información técnica</summary>
			<p>Información de soporte:<%=exception.getCause().getMessage()%></p>
		</details>
				
		
	</body>
</html>