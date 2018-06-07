package org.pracjeelibros.dao;

import java.io.Serializable;
import java.util.List;

public interface EntityDao<T, Id extends Serializable> {
	
	T buscarPorClave(Id id);
	
	List<T> buscarTodos();
	
	void insertar(T t);
	
	void actualizar(T t);
	
	void borrar(T t);
	
	
	
	

}
