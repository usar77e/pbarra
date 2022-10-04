package com.daniele.pbarra.manager.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.daniele.pbarra.manager.CajaManager;
import com.daniele.pbarra.model.Caja;
import com.daniele.pbarra.repository.CajaRepository;

import exceptions.ModelNotFoundException;

public class CajaManagerImpl implements CajaManager{

	@Autowired
	private CajaRepository cajarepo;
	
	@Override
	public List<Caja> findAll() {
		// TODO Auto-generated method stub
		return cajarepo.findAll();
	}

	@Override
	public Caja findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Caja> caja = cajarepo.findById(id);
		if(caja.isPresent()) {
			return caja.get();
		}
		throw new ModelNotFoundException("Caja no encontrada");
	}

	@Override
	public Caja save(Caja e) {
		// TODO Auto-generated method stub
		Caja caja = new Caja();
		try {
			caja = cajarepo.save(e);
		} catch(Exception ex) {
			throw new ModelNotFoundException("Caja no encontrada");
		}
		return caja;
	}

	@Override
	public Caja update(Caja e) {
		// TODO Auto-generated method stub
		return save(e);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		cajarepo.deleteById(id);
		return true;
	}
	
}
