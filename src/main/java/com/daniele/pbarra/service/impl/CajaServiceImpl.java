package com.daniele.pbarra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniele.pbarra.manager.CajaManager;
import com.daniele.pbarra.model.Caja;
import com.daniele.pbarra.service.CajaService;

@Service
public class CajaServiceImpl implements CajaService{

	@Autowired
	private CajaManager cajamana;
	
	public CajaServiceImpl(CajaManager cajamana) {
		this.cajamana = cajamana;
	}

	@Override
	public List<Caja> findAll() {
		// TODO Auto-generated method stub
		return cajamana.findAll();
	}

	@Override
	public Caja findById(Integer id) {
		// TODO Auto-generated method stub
		return cajamana.findById(id);
	}

	@Override
	public Caja save(Caja e) {
		// TODO Auto-generated method stub
		return cajamana.save(e);
	}

	@Override
	public Caja update(Caja e) {
		// TODO Auto-generated method stub
		return cajamana.update(e);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return cajamana.delete(id);
	}
	
}
