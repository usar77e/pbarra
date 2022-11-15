package com.daniele.pbarra.manager.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniele.pbarra.manager.SucursalManager;
import com.daniele.pbarra.model.Sucursal;
import com.daniele.pbarra.repository.SucursalRepository;

import exceptions.ModelNotFoundException;
import exceptions.SucursalNoEncontradoException;

@Service
public class SucursalManagerImpl implements SucursalManager{

	@Autowired
	private SucursalRepository sucurepo;
	
	@Override
	public List<Sucursal> findAll() {
		// TODO Auto-generated method stub
		return sucurepo.findAll();
	}

	@Override
	public Sucursal findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Sucursal> sucursal = sucurepo.findById(id);
		if(sucursal.isPresent()) {
			return sucursal.get();
		}
		throw new SucursalNoEncontradoException("Sucursal no encontrada");
	}

	@Override
	public Sucursal save(Sucursal e) {
		// TODO Auto-generated method stub
		Sucursal sucursal = new Sucursal();
		try {
			sucursal = sucurepo.save(e);
		} catch(Exception ex) {
			throw new ModelNotFoundException("Sucursal no guardada");
		}
		return sucursal;
	}

	@Override
	public Sucursal update(Sucursal e) {
		// TODO Auto-generated method stub
		return save(e);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		sucurepo.deleteById(id);
		return true;
	}
	
}
