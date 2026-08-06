package com.coin.messages.service;

import com.coin.messages.model.Categoria;
import com.coin.messages.model.Producto;
import com.coin.messages.repository.CategoriaRepository;
import com.coin.messages.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;
    private final CategoriaRepository repoCategoria;

    public ProductoService(ProductoRepository repo, CategoriaRepository repoCategoria) {
        this.repo = repo;
        this.repoCategoria=repoCategoria;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto obtener(Long id){
        return repo.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado con id:"+id));
    }

    public Producto actualizar(Long id, Producto producto) {
        Producto exitente = repo.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado con id:"+id));

        Categoria categoria = repoCategoria.findById(producto.getCategoria().getId()).orElseThrow(()-> new RuntimeException("Categoria no encontrado con id:"+producto.getCategoria().getId()));
        producto.setCategoria(categoria);

        exitente.setNombre(producto.getNombre());
        exitente.setDescripcion(producto.getDescripcion());
        exitente.setCategoria(producto.getCategoria());
        exitente.setPrecio(producto.getPrecio());
        exitente.setStock(producto.getStock());
        exitente.setImagenUrl(producto.getImagenUrl());
        return  repo.save(exitente);
    }
    public Producto guardar(Producto p) {
        Categoria categoria = repoCategoria.findById(p.getCategoria().getId()).orElseThrow(()-> new RuntimeException("Categoria no encontrado con id:"+p.getCategoria().getId()));
        p.setCategoria(categoria);
        return repo.save(p);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
