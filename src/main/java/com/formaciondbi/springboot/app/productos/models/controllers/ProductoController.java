package com.formaciondbi.springboot.app.productos.models.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<Producto> listar(){
		return productoServices.findAll().stream().map(producto -> {
			//producto.setPuerto(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPuerto(puerto);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle (@PathVariable Long id) {
		Producto producto = productoServices.findId(id);
		//producto.setPuerto(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPuerto(puerto);
		return producto;
	}
}
