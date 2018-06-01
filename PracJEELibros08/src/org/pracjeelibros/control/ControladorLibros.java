package org.pracjeelibros.control;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pracjeelibros.Libro;

@WebServlet("*.do")
public class ControladorLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DESHACER_FILTRO = "seleccionar";
	private static final String ALL_BOOKS = "todos";
	private static final String ALL_CATEGORIES = "todas";
	private static final String FILTER_BOOKS = "filtrados";

	private static final String PREFIX = "/WEB-INF/vistas/";
	private static final String SHOW_BOOKS_CONTROLLER = "/showBooks.do";
	private static final String SHOW_BOOKS = PREFIX + "MostrarLibros.jsp";
	private static final String SHOW_FORM_ADD_BOOK = PREFIX + "FormAltaLibro.jsp";
	private static final String SHOW_FORM_EDIT_BOOK = PREFIX + "FormEdicionLibro.jsp";
	
	private static String NEXT_PAGE = null;

	public ControladorLibros() {
		super();

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		if (request.getServletPath().equals("/formAddBook.do")) {

			List<String> categorias = Libro.buscarTodasLasCategorias();
			request.setAttribute(ALL_CATEGORIES, categorias);
			
			NEXT_PAGE = SHOW_FORM_ADD_BOOK;

		} else if (request.getServletPath().equals("/addBook.do")) {
			
			insertOrUpdate(request, "insert");
			
		} else if (request.getServletPath().equals("/formEditBook.do")) {
			
			String isbn = request.getParameter("isbn");
			Libro libro = Libro.buscarPorClave(isbn);
			request.setAttribute("libro", libro);

			List<String> categorias = Libro.buscarTodasLasCategorias();
			request.setAttribute(ALL_CATEGORIES, categorias);
			
			NEXT_PAGE = SHOW_FORM_EDIT_BOOK;
		
		} else if (request.getServletPath().equals("/updateBook.do")) {			

			insertOrUpdate(request, "update");
		}
		else if (request.getServletPath().equals("/showBooks.do")) {
			
			List<Libro> libros = Libro.buscarTodos();
			List<String> categorias = Libro.buscarTodasLasCategorias();
			List<Libro> librosFiltrados = Collections.emptyList();// esta inicialitzat amb una llista buida, no esta
																	// null

			// mirem si ens ha arribat una categoria del navegador(usar combo)
			String categoria = request.getParameter("categoria");

			/*
			 * ¿El usuario quiere filtrar los libros por alguna categoria en particular?
			 */
			if (categoria != null && !categoria.isEmpty() && !categoria.equals(DESHACER_FILTRO)) {
				librosFiltrados = Libro.buscarPorCategoria(categoria);
			}

			// Guardamos las listas anteriores en las requests(buffer) para que estén a
			// disposición de las JSP's.

			request.setAttribute(ALL_BOOKS, libros);
			request.setAttribute(ALL_CATEGORIES, categorias);
			request.setAttribute(FILTER_BOOKS, librosFiltrados);

			NEXT_PAGE = SHOW_BOOKS;

		} else if (request.getServletPath().equals("/deleteBook.do")) {

			String isbn = request.getParameter("isbn");
			Libro libro = new Libro();
			libro.setIsbn(isbn);
			libro.borrar();

			NEXT_PAGE = SHOW_BOOKS_CONTROLLER;
		}

		request.getRequestDispatcher(NEXT_PAGE).forward(request, response);
	}

	private void insertOrUpdate(HttpServletRequest request, String operacion) {
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String categoria = request.getParameter("categoria");

		if (isbn == null || titulo == null || categoria == null || isbn.trim().isEmpty() || titulo.trim().isEmpty()
				|| categoria.equals("seleccionar")) {
			String mensaje = "Se ha intentado dar de alta/actualizar un libro con datos incompletos";
			throw new RuntimeException(mensaje);
		} else {
			Libro libro = new Libro(isbn, titulo, categoria);
			if(operacion.equals("insert"))			
				libro.insertar();
			else
				libro.actualizar();

		}

		NEXT_PAGE = SHOW_BOOKS_CONTROLLER;
	}

}
