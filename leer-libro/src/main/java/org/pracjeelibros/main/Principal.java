package org.pracjeelibros.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.pracjeelibros.model.Categoria;
import org.pracjeelibros.model.Libro;

public class Principal {

	public static void main(String[] args) throws IOException {
		String texto = "";
		Reader reader = new FileReader("libros.txt");
		BufferedReader lector = new BufferedReader(reader);
		List<Libro> lista = new ArrayList<Libro>();
		Libro libro = null;
		Categoria categoria = null;
		while ((texto = lector.readLine()) != null) {
			String[] datos = texto.split(",");
			categoria = new Categoria(Integer.parseInt(datos[2].trim()), datos[3].trim());
			libro = new Libro(datos[0].trim(), datos[1].trim(), categoria);
			lista.add(libro);
		}
		lector.close();
		for (Libro _libro : lista) {
			System.out.println(_libro.getTitulo());
		}
	}

}
