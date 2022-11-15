package com.daniele.pbarra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("findall")
	public ResponseEntity<List<Sucursal>> findAll() {
		return new ResponseEntity<>(sucserv.findAll(), HttpStatus.OK);
	}
	
	@GetMapping({"/{id}"})
	public Sucursal findById(
			@PathVariable("id") Integer id) {
		System.out.print("prueba");
		return sucserv.findById(id);
	}
	
	@PostMapping(value = "save")
	public @ResponseBody Sucursal save(@RequestBody Sucursal e) {
		return sucserv.save(e);
	}
}
