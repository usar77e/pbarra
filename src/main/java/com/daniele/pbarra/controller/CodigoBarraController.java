package com.daniele.pbarra.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.TextAlignment;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/codigo")
public class CodigoBarraController {
	
	//trae la ruta de la imagen
	private @Value("${ruta.template.plantillas}") String rutaTemplates;
	//trae la ruta de destino
	private @Value("${ruta.template.destino}") String rutadestino;
	
	
	//primera creacion manual de template cambio de tipo de imagen
	@ApiOperation(value = "Crea un codigo de barra")
    @ApiResponses({
      	@ApiResponse(message = "Operacion exitosa", code = 200),
          @ApiResponse(message = "No encontrada", code = 404),
          @ApiResponse(message = "Sin autorizacion", code = 401),
          @ApiResponse(message = "Requiere autenticacion de Proxy", code = 407),
          @ApiResponse(message = "Largo minimo requerido", code = 411),
          @ApiResponse(message = "Falla interna servidor", code = 500),
      	@ApiResponse(message = "No implementado", code = 501),
      	@ApiResponse(message = "Respuesta invalida - Bad gateway", code = 502),
      	@ApiResponse(message = "Servicio fuera de alcance", code = 503),
      	@ApiResponse(message = "Gateway Tinme out", code = 504)
      	})
	@RequestMapping(value = "/barra", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public FileSystemResource createBarra(
			@RequestParam(value = "valores", required = true) String valores 
			) {
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
					//algo = generateEAN13BarcodeImage("123456789012"); 
					algo = generateEAN13BarcodeImage(160, valores);
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
	
	@ApiOperation(value = "Crea un codigo de barra")
    @ApiResponses({
      	@ApiResponse(message = "Operacion exitosa", code = 200),
          @ApiResponse(message = "No encontrada", code = 404),
          @ApiResponse(message = "Sin autorizacion", code = 401),
          @ApiResponse(message = "Requiere autenticacion de Proxy", code = 407),
          @ApiResponse(message = "Largo minimo requerido", code = 411),
          @ApiResponse(message = "Falla interna servidor", code = 500),
      	@ApiResponse(message = "No implementado", code = 501),
      	@ApiResponse(message = "Respuesta invalida - Bad gateway", code = 502),
      	@ApiResponse(message = "Servicio fuera de alcance", code = 503),
      	@ApiResponse(message = "Gateway Tinme out", code = 504)
      	})
	@RequestMapping(value = "/barra-v2", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public FileSystemResource createbarrav2(
			@RequestParam(value = "codigo", required = true) String codigo,
			@RequestParam(value = "template", required = true) String template,
			@RequestParam(value = "ruta", required = true) String ruta,
			@RequestParam(value = "extension", required = true) String extension,
			//@RequestParam(value = "bufx", required = true) Integer bufx,
			//@RequestParam(value = "bufy", required = true) Integer bufy,
			@RequestParam(value = "nombreBarra", required = true) String nombreImagenBarra,
			@RequestParam(value = "tamanioBarra", required = true) Integer tamanioBarra,
			@RequestParam(value = "nombreFinal", required = true) String nombreFinal,
			@RequestParam(value = "poscodigox", required = true) Integer poscodigox,
			@RequestParam(value = "poscodigoy", required = true) Integer poscodigoy,
			@RequestParam(value = "postitulox", required = true) Integer postitulox,
			@RequestParam(value = "postituloy", required = true) Integer postituloy,
			@RequestParam(value = "sizetitulo", required = true) Integer sizetitulo
			
			) {
		File target = new File(ruta + template);
		BufferedImage codigoBarra = null;
		Integer templateHeight = 0, templateWidth = 0, barraHeight = 0, barraWidth = 0;
		if(target.exists()) {
			System.out.print("Existe");
			try {
				//llama al metodo para crear el codigo de barra ->  1
				codigoBarra = generateEAN13BarcodeImage(tamanioBarra, codigo);
				
				//ubicacion de la creacion del archivo ->  2
				File rutaDestino = new File( rutadestino + nombreImagenBarra + "." + extension);
				//creacion del archivo ->  3 / aca crea el archivo de codigo de barra
				//tiene que ir a la ruta dl codigo de barra
				ImageIO.write(codigoBarra, extension, rutaDestino);
				
				//lee la imagen creada ->  4
				BufferedImage imagenBarra = ImageIO.read(rutaDestino);
				
				//saca el valor ancho y el alto del codigo de barra seleccionada
				barraHeight = imagenBarra.getHeight();
				barraWidth = imagenBarra.getWidth();
				
				//lee el fondo ->  5 
				BufferedImage imagenFondo = ImageIO.read(target);
				
				//saca el valor ancho y el alto del template seleccionada
				templateHeight = imagenFondo.getHeight();
				templateWidth = imagenFondo.getWidth();
				Integer posWidth =  templateWidth - ((templateWidth * poscodigox) /100);
				Integer posHeight = templateHeight - ((templateHeight * poscodigoy) /100);
				
				//se crea la imagen en donde se juntara todo ->  6
				//BufferedImage imagenFinal = new BufferedImage(bufx, bufy, BufferedImage.TYPE_INT_ARGB);
				BufferedImage imagenFinal = new BufferedImage(templateWidth, templateHeight, BufferedImage.TYPE_INT_ARGB);
				
				//manipulador 2g ->  7
				Graphics2D grap = imagenFinal.createGraphics();
				//agregar texto al template
				grap.setPaint(Color.BLACK);
				grap.setFont(new Font("Helvetica medium", Font.BOLD, sizetitulo));
				//grap.drawString("Hola Mundo", 1000, 1000);
				
				// ->  8
				//grap.drawImage(imagenFondo, 0, 0, 1692, 1692, null);
				//dimensiones de imagen de fondo -> tiene que tener las mismas dimensiones que la imagen de fondo
				//grap.drawImage(imagenFondo, 0, 0, 500, 250, null);
				grap.drawImage(imagenFondo, 0, 0, templateWidth, templateHeight, null);
				//->  9
				//ubicacion del codigo de barra dentro del template
				//grap.drawImage(imagenFinal, 45, 45, null);
				grap.drawImage(imagenBarra, posWidth, posHeight, barraWidth, barraHeight, null);
				grap.drawString("Hola Mundo", postitulox, postituloy);
				//->  10
				ImageIO.write(imagenFinal, extension, new File(rutadestino + nombreFinal + "." + extension));
			} catch (Exception ex) {
				System.out.print("Excepcion " + ex);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		} else {
			System.out.print("No existe un archivo en esa ruta");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return null;
	}
	
	
	public static BufferedImage generateEAN13BarcodeImage(Integer tamanioBarra, String barcodeText) {
	    EAN13Bean barcodeGenerator = new EAN13Bean();
	    BitmapCanvasProvider canvas = 
	      //new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	      new BitmapCanvasProvider(tamanioBarra, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	    
	    //prueba con metodos propios de BitmapCanvasProvider
	    //hasta el momento pruebas no reflejan cambios en el producto final
	    //son un fracaso
	    //establecer dimensiones
	    //BarcodeDimension dim = new BarcodeDimension(414, 84);
	    //BarcodeDimension dim = new BarcodeDimension(828, 168);
	    //canvas.establishDimensions(dim);
	    
	    //draw text, prueba de creacion de texto en
	    
	    //TextAlignment typecenter = TextAlignment.TA_CENTER;
	    //TextAlignment typejustify = TextAlignment.TA_JUSTIFY;
	      
	    //String texto = "写的比较马虎 这里仅仅是一个思路吧";
	    //canvas.deviceText("Hola mundo", 100, 100, 100, "Serif", 100, typecenter);
	    //canvas.deviceText(texto, 100, 100, 100, "Dialog", 1000, typecenter);
	    
	    barcodeGenerator.generateBarcode(canvas, barcodeText);
	    return canvas.getBufferedImage();
	}
}
