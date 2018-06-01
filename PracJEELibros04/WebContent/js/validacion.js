var opacidadStatus = 1;
var idCallbackTransparencia = null;

function validacion() {
	const isbn = document.getElementById("isbn")
	const titulo = document.getElementById("titulo")
	const categoria = document.getElementById("categoria")
	
	const formulario = document.getElementById("datos")
	
	if (isbn.value == "") {
		showError("El ISBN ha de tener valor");
		return false;
	} 
	
	if (titulo.value == "") {
		showError("El título ha de tener valor");
		return false;
	}
	
	if (categoria.selectedIndex == 0) {
		showError("La categoria ha de tener valor");
		return false;
	}	
	
	return true;
}

function showError(mensaje) {
	var status = document.getElementById("status");
	status.innerHTML = mensaje
	status.style.display = "inline-block";
	
	document.getElementById("isbn").focus()
	
	// Activar contador para cuentra atras limpieza error
	//setTimeout(clearError, 3000)
	idCallbackTransparencia = setInterval(desvanecerStatus, 200)
}

function clearError() {			
	idCallbackTransparencia = setInterval(desvanecerStatus, 200)
}

function desvanecerStatus() {
	var status = document.getElementById("status");
	if (opacidadStatus <= 0) {
		status.style.innerHTML = "";
		clearTimeout(idCallbackTransparencia)
		opacidadStatus = 1
	} else {				
		opacidadStatus -= 0.1
		status.style.opacity = opacidadStatus;
	}
}