package com.coin.messages.service;

import com.coin.messages.dto.request.ProductoRequest;
import com.coin.messages.dto.response.ProductoResponse;
import com.coin.messages.model.Categoria;
import com.coin.messages.model.Producto;
import com.coin.messages.repository.CategoriaRepository;
import com.coin.messages.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import com.coin.messages.mapper.ProductoMapper;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;
    private final CategoriaRepository repoCategoria;
    private final ProductoMapper mapper;

    public ProductoService(ProductoRepository repo, CategoriaRepository repoCategoria, ProductoMapper mapper) {
        this.repo = repo;
        this.repoCategoria=repoCategoria;
        this.mapper=mapper;
    }

    public List<ProductoResponse> listar() {
        return repo.findAll().stream().map(mapper::toProductoResponse).toList();
    }

    public ProductoResponse obtener(Long id){
        Producto producto = repo.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado con id:"+id));
        return  mapper.toProductoResponse(producto);
    }

    public ProductoResponse actualizar(Long id, ProductoRequest productoRequest) {
        Producto exitente = repo.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado con id:"+id));
        Categoria categoria = repoCategoria.findById(productoRequest.getCategoriaId()).orElseThrow(()-> new RuntimeException("Categoria no encontrado con id:"+productoRequest.getCategoriaId()));
        mapper.updateProducto(exitente, productoRequest, categoria);

        Producto actualizado = repo.save(exitente);
        return  mapper.toProductoResponse(actualizado);
    }
    public ProductoResponse guardar(ProductoRequest p) {
        Categoria categoria = repoCategoria.findById(p.getCategoriaId()).orElseThrow(()-> new RuntimeException("Categoria no encontrado con id:"+p.getCategoriaId()));
        Producto producto = new Producto();
        mapper.updateProducto(producto,p,categoria);
        Producto guardado = repo.save(producto);

        return mapper.toProductoResponse(guardado);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        repo.deleteById(id);
    }
}
