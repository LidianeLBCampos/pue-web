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

/**
 * Servlet implementation class ControladorLibros
 */
@WebServlet("*.do")
public class ControladorLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DESHACER_FILTRO = new String("seleccionar");
	private static final String ALL_BOOKS = new String("todos");
	private static final String ALL_CATEGORIES = new String("todas");
	private static final String FILTER_BOOKS = new String("filtrados");
	private static final String SHOW_BOOKS = new String("/showBooks");
	private static final String SHOW_FORM_ADD_BOOK = new String("/formAddBook");
	private static final String SHOW_FORM_EDIT_BOOK = new String("/editBook");
	private static final String BOOK = new String("libro");
	
	private static String NEXT_PAGE = "";
	
    public ControladorLibros() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<String> categorias = Libro.buscarTodasLasCategorias();
		String isbn,titulo,categoria = new String();
		Libro libro = new Libro();
		List<String> listaCat = Collections.EMPTY_LIST;
		
		switch ( request.getServletPath() ) {
		case "/formAddBook.do":
			System.out.println("formAddBook.do");
			request.setAttribute(ALL_CATEGORIES,categorias);
			
			NEXT_PAGE = SHOW_FORM_ADD_BOOK;
			break;
			
		case "/addBook.do" :
			// <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			System.out.println("addbook.do");
			request.setCharacterEncoding("UTF-8");
			
			isbn = request.getParameter("isbn");
			titulo = request.getParameter("titulo");
			categoria = request.getParameter("categoria");
			
			if (isbn == null || titulo == null || categoria == null
					|| isbn.isEmpty() || titulo.isEmpty() || categoria.isEmpty()) {
				
				String mensaje = "Se ha intentado dar de alta un libro con datos imcompletos";
				throw new RuntimeException(mensaje);	
			} else {
				libro = new Libro(isbn, titulo, categoria);
				libro.insertar();
				request.removeAttribute("categoria");
			}
				
			NEXT_PAGE = "/showBooks.do";
			break;
		
		case "/formUpdateBook.do":
			System.out.println("formUpdateBook.do");
			isbn = request.getParameter("isbn");
			libro = Libro.buscarPorClave(isbn);
			listaCat = Libro.buscarTodasLasCategorias();
			request.setAttribute(ALL_CATEGORIES,categorias);
			request.setAttribute(BOOK,libro);
			NEXT_PAGE = SHOW_FORM_EDIT_BOOK;
			break;
			
		case "/updateBook.do":
			System.out.println("updateBook.do");
			request.setCharacterEncoding("UTF-8");
			
			isbn = request.getParameter("isbn");
			titulo = request.getParameter("titulo");
			categoria = request.getParameter("categoria");
			
			libro = new Libro(isbn, titulo, categoria);
			System.out.println(isbn+"-"+titulo+"-"+categoria);
			
			libro.actualizar();
			NEXT_PAGE = "/showBooks.do";
			break;
		
		case "/deleteBook.do":
			System.out.println("deleteBook.do");
			isbn = request.getParameter("isbn");
			libro = new Libro();
			libro.setIsbn(isbn);
			libro.borrar();
			
			NEXT_PAGE = "/showBooks.do";

			break;

		case "/showBooks.do":
			System.out.println("showBooks.do");
			List<Libro> libros = Libro.buscarTodos();
			List<Libro> librosFiltrados = Collections.emptyList();
			String fcategoria = request.getParameter("fcategoria");
			
			if ( fcategoria != null 
					&& !fcategoria.isEmpty() 
					&& !fcategoria.equals(DESHACER_FILTRO) ) {
				librosFiltrados = Libro.buscarPorCategoria(fcategoria);	
			}
			
			request.setAttribute(ALL_BOOKS,libros);
			request.setAttribute(ALL_CATEGORIES,categorias);
			request.setAttribute(FILTER_BOOKS,librosFiltrados);
			
			NEXT_PAGE = SHOW_BOOKS;
			break;
			
		default:
			NEXT_PAGE = SHOW_BOOKS;
		}
		
		request.getRequestDispatcher(NEXT_PAGE).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	
}
