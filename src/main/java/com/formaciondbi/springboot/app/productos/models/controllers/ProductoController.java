package com.formaciondbi.springboot.app.productos.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.springboot.app.productos.models.entity.Producto;
import com.formaciondbi.springboot.app.productos.models.services.IProductoServices;

@RestController
public class ProductoController {

	@Autowired
	private IProductoServices productoServices;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoServices.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle (@PathVariable Long id) {
		return productoServices.findId(id);
	}
}
