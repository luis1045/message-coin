package com.coin.messages.controller;

import com.coin.messages.dto.request.ProductoRequest;
import com.coin.messages.dto.response.ProductoResponse;
import com.coin.messages.model.Producto;
import com.coin.messages.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProductoResponse obtener(@PathVariable Long id){
        return service.obtener(id);
    }
    @PutMapping("/{id}")
    public ProductoResponse actualizar(@PathVariable Long id, @RequestBody ProductoRequest producto){
        return service.actualizar(id, producto);
    }

    @PostMapping
    public ProductoResponse guardar(@RequestBody ProductoRequest p) {
        return service.guardar(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}