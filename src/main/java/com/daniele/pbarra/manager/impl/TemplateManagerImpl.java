package com.daniele.pbarra.manager.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniele.pbarra.manager.TemplateManager;
import com.daniele.pbarra.model.Template;
import com.daniele.pbarra.repository.TemplateRepository;

import exceptions.ModelNotFoundException;
import exceptions.TemplateNoEcontradoException;

@Service
public class TemplateManagerImpl implements TemplateManager{

	@Autowired
	private TemplateRepository temrepo;
	
	@Override
	public List<Template> findAll() {
		// TODO Auto-generated method stub
		return temrepo.findAll();
	}

	@Override
	public Template findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Template> template = temrepo.findById(id);
		if(template.isPresent()) {
			return template.get();
		}
		throw new TemplateNoEcontradoException("Template no encontrado");
	}

	@Override
	public Template save(Template e) {
		// TODO Auto-generated method stub
		Template template = new Template();
		try {
			template = temrepo.save(e);
		} catch(Exception ex) {
			throw new ModelNotFoundException("Template no guardado");
		}
		return template;
	}

	@Override
	public Template update(Template e) {
		// TODO Auto-generated method stub
		return save(e);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		temrepo.deleteById(id);
		return true;
	}
	
}
