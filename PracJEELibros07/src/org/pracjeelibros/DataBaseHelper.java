package org.pracjeelibros;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DataBaseHelper<T> {

	private static final Logger log = Logger.getLogger(DataBaseHelper.class);
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final
		String URL = "jdbc:mysql://localhost/pracLibrosJEE";
	private static final String USUARIO = "root";
	private static final String CLAVE = "toor";
	
	public int modificarRegistro(String consultaSQL) {
		int filas = 0;
		
		try {	
			Class.forName(DRIVER);
			
			try (
				Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
				Statement sentencia = conexion.createStatement();
			) {		
				filas = sentencia.executeUpdate(consultaSQL);				
			} catch (SQLException e) {
				log.error("Error accediendo a la base de datos" + e.getMessage());
//				throw new DataBaseException("Error accediendo a la base de datos",e);
			}		
		} catch (ClassNotFoundException e) {
			log.error("Error en la carga del driver" + e.getMessage());
			throw new DataBaseException("Error en la carga del driver",e);
		}
		return filas;
	}
	
	/*
	 * Este método es capaz de ejecutar una consulta de cualquier índole y a
	 * partir del ResultSet correspondiente crear una lista del tipo especificado:
	 * Libro, Categoria, String, etc.
	 * */
	public List<T> seleccionarRegistros(String consultaSQL, Class<T> clase) {
		
		List<T> lista = null;
		
		try {
			Class.forName(DRIVER);			
			lista = new ArrayList<>();
			
			try (Connection conexion = 
				DriverManager.getConnection(URL, USUARIO, CLAVE);
				Statement sentencia = conexion.createStatement();
				ResultSet rs = sentencia.executeQuery(consultaSQL))
			{							
				while (rs.next()) {
					procesarFila(rs, lista, clase);
				}								
			} catch (Exception e) {
				log.error("Error: " + e.getMessage());
				throw new DataBaseException("Error accediendo a la base de datos",e);
			}					
		} catch (ClassNotFoundException e) {
			log.error("Error en la carga del driver" + e.getMessage());
			throw new DataBaseException("Error en la carga del driver",e);
		}
		
		return lista;
	}

	private void procesarFila(ResultSet rs, List<T> lista, Class<T> clase) 
			throws Exception 
	{		
		String fqdn = clase.getName();

		// Crear un nuevo objeto pero de manera reflexiva
		T objeto = (T) Class.forName(fqdn).newInstance();
		
		// Obtener un array con todos los métodos del objeto
		Method[] metodos = objeto.getClass().getDeclaredMethods();
		
		for(Method metodo : metodos) {
			String nomMetodo = metodo.getName();
			if (nomMetodo.startsWith("set")) {
				String nomCampo = nomMetodo.substring(3).toLowerCase();
				String valorCampo = rs.getString(nomCampo);
				
				// Ejecutar el setter reflexivamente
				metodo.invoke(objeto, valorCampo);								
			}
			if (fqdn.equals("java.lang.String")) {
				objeto = (T) rs.getString("categoria");
			}
		}
		// En este punto el objeto ya está correctamente "setteado"
		lista.add(objeto);
	}
	
}




