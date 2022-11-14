package com.daniele.pbarra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daniele.pbarra.model.Template;
import com.daniele.pbarra.service.TemplateService;

@RestController
@RequestMapping(value = "/template")
public class TemplateController {
	
	@Autowired
	private TemplateService tempserv;
	
	@GetMapping("findall")
	public ResponseEntity<List<Template>> findAll(){
		return new ResponseEntity<>(tempserv.findAll(), HttpStatus.OK);
	}
	
	@GetMapping({"/{id}"})
	public Template findById(
			@PathVariable("id") Integer id
			) {
		return tempserv.findById(id);
	}
	
	@PostMapping(value = "/save")
	public @ResponseBody Template save(@RequestBody Template e) {
		return tempserv.save(e);
	}
	
	
}
