package com.daniele.pbarra.controller;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daniele.pbarra.model.Caja;
import com.daniele.pbarra.service.CajaService;

@RestController
@RequestMapping(value = "/caja")
public class CajaController {
	@Autowired
	private CajaService cajaserv;
	
	//trae la ruta de la imagen
	private @Value("${ruta.template.plantillas}") String rutaTemplates;
	
	@GetMapping("/findall")
	public ResponseEntity<List<Caja>> findAll(){
		return new ResponseEntity<>(cajaserv.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Caja> findById(
			@PathVariable("id") Integer id
			) {
		return new ResponseEntity<Caja>(cajaserv.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity save(@RequestBody Caja e) throws URISyntaxException{
		Caja caja = cajaserv.save(e);
		return ResponseEntity.created(new URI("/caja/" + caja.getId())).body(caja);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCaja(
			@PathVariable("id") Integer id
			){
		cajaserv.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gettemplate", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public FileSystemResource createTemplate() {
		System.out.print("Llego!");
		//revisa ruta del archivo template
		File target = new File(rutaTemplates + "fondo_blanco.png");
		if(target.exists()) {
			System.out.print("Existe");
		} else {
			System.out.print("No existe");
		}
		return null;
	}

}
