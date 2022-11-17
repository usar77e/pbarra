package com.daniele.pbarra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daniele.pbarra.model.Sucursal;
import com.daniele.pbarra.service.SucursalService;

@RestController
@RequestMapping(value = "/sucursal")
public class SucursalController {
	@Autowired
	private SucursalService sucserv;
	
	@GetMapping("/findall")
	public ResponseEntity<List<Sucursal>> findAll() {
		return new ResponseEntity<>(sucserv.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Sucursal> findById(
			@PathVariable("id") Integer id) {
		System.out.print("prueba");
		return new ResponseEntity<Sucursal>(sucserv.findById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public @ResponseBody Sucursal save(@RequestBody Sucursal e) {
		return sucserv.save(e);
	}
	
	@PutMapping
	public ResponseEntity<Sucursal> modificar(@RequestBody Sucursal sucursal){
		Sucursal suc = sucserv.update(sucursal);
		return new ResponseEntity<Sucursal>(suc, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteSucursal(@PathVariable("id") Integer id) throws Exception {
		sucserv.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
