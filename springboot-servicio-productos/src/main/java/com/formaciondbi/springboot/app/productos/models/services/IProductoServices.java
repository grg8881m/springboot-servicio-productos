package com.formaciondbi.springboot.app.productos.models.services;

import java.util.List;

import com.formaciondbi.springboot.app.productos.models.entity.Producto;

public interface IProductoServices {
	List<Producto> findAll();
	Producto findId(Long id);
}
