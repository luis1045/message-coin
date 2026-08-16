package com.coin.messages.controller;

import com.coin.messages.dto.request.CategoriaRequest;
import com.coin.messages.dto.response.CategoriaResponse;
import com.coin.messages.model.Categoria;
import com.coin.messages.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin
public class CategoriaController {
    private final CategoriaService service;
    public CategoriaController(CategoriaService service){
        this.service = service;
    }

    @GetMapping
    public List<CategoriaResponse> listar(){
        return  service.listar();
    }
    @GetMapping("/{id}")
    public CategoriaResponse obtener(@PathVariable Long id){
        return service.obtener(id);
    }
    @PostMapping
    public CategoriaResponse guardar(@RequestBody CategoriaRequest categoria){
        return  service.guardar(categoria);
    }
    @PutMapping("/{id}")
    public CategoriaResponse actualziar(@PathVariable Long id, @RequestBody CategoriaRequest categoria) {
        return service.actualziar(id,categoria);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.eliminar(id);
    }
}
