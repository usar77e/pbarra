package com.daniele.pbarra.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/findall")
	public ResponseEntity<List<Template>> findAll(){
		return new ResponseEntity<>(tempserv.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Template> findById(
			@PathVariable("id") Integer id
			) {
		return new ResponseEntity<Template>(tempserv.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody Template e) throws URISyntaxException{
		Template temp =  tempserv.save(e);
		return ResponseEntity.created(new URI("/template/" + temp.getId())).body(temp);
	}
	
	@PutMapping
	public ResponseEntity<Template> modificar(@RequestBody Template template){
		Template temp = tempserv.update(template);
		return new ResponseEntity<Template>(temp, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTemplate(@PathVariable("id") Integer id) throws Exception{
		tempserv.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
