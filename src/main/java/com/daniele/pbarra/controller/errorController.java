package com.daniele.pbarra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class errorController {
	
	//es solo una clase de prueba en el manejo de excepciones
	
	@SuppressWarnings("unused")
	@GetMapping("/index")
	public String index() {
		Integer valor =  Integer.parseInt("10tx");
		return "index";
	}
}
