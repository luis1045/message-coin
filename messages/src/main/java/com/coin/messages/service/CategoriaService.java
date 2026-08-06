package com.coin.messages.service;

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

    public List<Categoria> listar(){
        return repository.findAll();
    }
    public Categoria obtener(Long id){
        return repository.findById(id).orElseThrow(()->new RuntimeException("No se encontro la categoria con id:"+id) );
    }
    public Categoria guardar(Categoria categoria){
        return repository.save(categoria);
    }
    public Categoria actualziar(Long id, Categoria categoria){
        Categoria existe = repository.findById(id).orElseThrow(()->new RuntimeException("No existe la categoria"+id) );
        existe.setNombre(categoria.getNombre());
        existe.setDescripcion(categoria.getDescripcion());
        return repository.save(existe);
    }
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
