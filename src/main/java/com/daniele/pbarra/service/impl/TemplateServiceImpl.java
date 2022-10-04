package com.daniele.pbarra.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniele.pbarra.manager.TemplateManager;
import com.daniele.pbarra.model.Template;
import com.daniele.pbarra.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService{

	@Autowired
	private TemplateManager tempman;
	
	public TemplateServiceImpl(TemplateManager tempman) {
		this.tempman = tempman;
	}

	@Override
	@Transactional
	public List<Template> findAll() {
		// TODO Auto-generated method stub
		return tempman.findAll();
	}

	@Override
	public Template findById(Integer id) {
		// TODO Auto-generated method stub
		return tempman.findById(id);
	}

	@Override
	public Template save(Template e) {
		// TODO Auto-generated method stub
		return tempman.save(e);
	}

	@Override
	public Template update(Template e) {
		// TODO Auto-generated method stub
		return tempman.update(e);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return tempman.delete(id);
	}
	
}
