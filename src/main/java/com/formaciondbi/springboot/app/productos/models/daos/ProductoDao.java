package com.formaciondbi.springboot.app.productos.models.daos;

import org.springframework.data.repository.CrudRepository;

import com.formaciondbi.springboot.app.productos.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {

}
