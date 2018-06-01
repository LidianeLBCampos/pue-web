package org.pracjeelibros;

public class DataBaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DataBaseException() {
		super();
	}

	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataBaseException(String message) {
		super(message);
	}

	public DataBaseException(Throwable cause) {
		super(cause);
	}
	
	private String getHTML(String titulo, String detalle) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='alert alert-danger' role='alert'>");
		sb.append("<b>" + titulo + "</b>");
		sb.append("<p>" + detalle + "</p>");
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public String showHTMLError(Throwable e) {
		String titulo = e.getMessage();
		String detalle = e.getCause().getMessage();
		System.out.println(titulo + "/n" + detalle);

		return this.getHTML(titulo,detalle);
	}
}
