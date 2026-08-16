package com.coin.messages.service;

import com.coin.messages.dto.request.CategoriaRequest;
import com.coin.messages.dto.response.CategoriaResponse;
import com.coin.messages.model.Categoria;
import com.coin.messages.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private  final CategoriaRepository repository;

    private CategoriaService(CategoriaRepository repository){
        this.repository=repository;
    }

    public List<CategoriaResponse> listar(){
        return repository.findAll().stream().map(this::convertirResponse).toList();
    }
    public CategoriaResponse obtener(Long id){
        Categoria categoria = repository.findById(id).orElseThrow(()->new RuntimeException("No se encontro la categoria con id:"+id) );
        return convertirResponse(categoria);
    }
    public CategoriaResponse guardar(CategoriaRequest categoriaRequest){
        Categoria categoria  = new Categoria();
        categoria.setNombre(categoriaRequest.getNombre());
        categoria.setDescripcion(categoriaRequest.getDescripcion());
        Categoria guardar = repository.save(categoria);

        return this.convertirResponse(guardar);
    }
    public CategoriaResponse actualziar(Long id, CategoriaRequest categoriaRequest){
        Categoria existe = repository.findById(id).orElseThrow(()->new RuntimeException("No existe la categoria"+id) );
        existe.setNombre(categoriaRequest.getNombre());
        existe.setDescripcion(categoriaRequest.getDescripcion());
        Categoria actualziar = repository.save(existe);
        return this.convertirResponse(actualziar);
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw  new RuntimeException("No existe el categoria con id:"+id);
        }
        repository.deleteById(id);
    }

    //ES OTRA MANERA DE HACER EL CASTEO DE TIPOS PERO ES MEJOR UNAR UN MAPER
    private CategoriaResponse convertirResponse(Categoria categoria){
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}
