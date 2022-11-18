package com.daniele.pbarra.controller;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.imageio.ImageIO;

import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
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
	//trae la ruta de destino
	private @Value("${ruta.template.destino}") String rutadestino;
	
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
		String codigoDeBarra = "";
		BufferedImage algo = null;
		if(target.exists()) {
			System.out.print("Existe");
		} else {
			System.out.print("No existe");
		}
		try {
			algo = generateEAN13BarcodeImage("123456789012");
			//ImageIO.write(algo, "png", target); rutadestino
			File rutaDestino = new File(rutadestino + "codigo_barra.png");
			ImageIO.write(algo, "png", rutaDestino);
			//lee la imagen recien creada
			BufferedImage imagenbarra = ImageIO.read(rutaDestino);
			//lee el fondo, template en el cual se dejara la imagen
			BufferedImage imagenFondo = ImageIO.read(new File(rutaTemplates + "fondo_blanco.png"));
			
			//se crea la imagen en la cual se juntara todo
			BufferedImage imagenFinal = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
			
			//creo manipulador 2g
			Graphics2D grap = imagenFinal.createGraphics();
			//grap.setFont(new Font("Helvetica medium", Font.BOLD, 50));
			//dibuja ambas imagenes en la imagen creada
			grap.drawImage(imagenFondo, 0, 0, 1692, 1692, null);
			grap.drawImage(imagenbarra, 45, 45, null);
			
			//crea la imagen en el computador
			ImageIO.write(imagenFinal, "png", new File(rutadestino + "imagen_final.png"));
			
			//imagenFondo = ImageIO.read(target);
			
			//BufferedImage imagenbarra = ImageIO.read(new File(rutadestino + "codigoBarra.png"));
			System.out.print(algo);
		}catch (Exception e ) {
			System.out.print("Error " + e.getMessage());
		}
		
		
		
		return null;
	}
	
	public static BufferedImage generateEAN13BarcodeImage(String barcodeText) {
	    EAN13Bean barcodeGenerator = new EAN13Bean();
	    BitmapCanvasProvider canvas = 
	      new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	    barcodeGenerator.generateBarcode(canvas, barcodeText);
	    return canvas.getBufferedImage();
	}

}
