package com.coin.messages.mapper;

import com.coin.messages.controller.ProductoController;
import com.coin.messages.dto.request.ProductoRequest;
import com.coin.messages.dto.response.ProductoResponse;
import com.coin.messages.model.Categoria;
import com.coin.messages.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponse toProductoResponse(Producto producto) {
        ProductoResponse dto = new ProductoResponse();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setImagenUrl(producto.getImagenUrl());

        dto.setCategoriaId(producto.getCategoria().getId());
        dto.setCategoriaNombre(producto.getCategoria().getNombre());
        return dto;
    }

    public void updateProducto(Producto producto, ProductoRequest productoRequestproducto, Categoria categoria) {
        producto.setNombre(productoRequestproducto.getNombre());
        producto.setDescripcion(productoRequestproducto.getDescripcion());
        producto.setPrecio(productoRequestproducto.getPrecio());
        producto.setStock(productoRequestproducto.getStock());
        producto.setImagenUrl(productoRequestproducto.getImagenUrl());
        producto.setCategoria(categoria);
    }
}
