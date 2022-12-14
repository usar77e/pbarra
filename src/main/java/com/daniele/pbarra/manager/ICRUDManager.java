package com.daniele.pbarra.manager;

import java.util.List;

public interface ICRUDManager<E> {

	List<E> findAll();
	
	E findById(Integer id);
	
	E save(E e);
	
	E update(E e);
	
	boolean delete(Integer id);
}
