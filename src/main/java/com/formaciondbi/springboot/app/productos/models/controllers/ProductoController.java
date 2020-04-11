package com.formaciondbi.springboot.app.productos.models.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.springboot.app.productos.models.entity.Producto;
import com.formaciondbi.springboot.app.productos.models.services.IProductoServices;

@RestController
public class ProductoController {

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer puerto;

	@Autowired
	private IProductoServices productoServices;

	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoServices.findAll().stream().map(producto -> {
			// producto.setPuerto(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPuerto(puerto);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		Producto producto = productoServices.findId(id);
		// producto.setPuerto(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPuerto(puerto);

		/*
		 * boolean ok = false;
		 * 
		 * if (!ok) { throw new Exception("No es posible cargar el producto"); }
		 */

		/*
		 * try { Thread.sleep(2000L); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */

		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoServices.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@PathVariable Long id, @RequestBody Producto producto) {
		Producto productoDB = productoServices.findId(id);
		productoDB.setNombre(producto.getNombre());
		productoDB.setPrecio(producto.getPrecio());
		return productoServices.save(productoDB);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoServices.deleteForId(id);
	}
}
