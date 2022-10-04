package com.daniele.pbarra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniele.pbarra.manager.SucursalManager;
import com.daniele.pbarra.model.Sucursal;
import com.daniele.pbarra.service.SucursalService;

@Service
public class SucursalServiceImpl implements SucursalService{

	
	@Autowired
	private SucursalManager sucumana;
	
	
	public SucursalServiceImpl(SucursalManager sucumana) {
		this.sucumana = sucumana;
	}

	@Override
	public List<Sucursal> findAll() {
		// TODO Auto-generated method stub
		return sucumana.findAll();
	}

	@Override
	public Sucursal findById(Integer id) {
		// TODO Auto-generated method stub
		return sucumana.findById(id);
	}

	@Override
	public Sucursal save(Sucursal e) {
		// TODO Auto-generated method stub
		return sucumana.save(e);
	}

	@Override
	public Sucursal update(Sucursal e) {
		// TODO Auto-generated method stub
		return sucumana.update(e);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return sucumana.delete(id);
	}
	
}
