package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.entity.Categoria;
import com.ifpb.ads.fliplearn.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Criar uma nova categoria
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Buscar todas as categorias principais (sem categoria pai)
    public List<Categoria> getCategoriasPrincipais() {
        return categoriaRepository.findByCategoriaPaiIsNull();
    }

    // Buscar subcategorias de uma categoria
    public List<Categoria> getSubcategorias(Long categoriaPaiId) {
        return categoriaRepository.findByCategoriaPaiId(categoriaPaiId);
    }

    // Buscar uma categoria por ID
    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    // Atualizar uma categoria
    public Categoria updateCategoria(Long id, Categoria categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId(id);
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    // Deletar uma categoria
    public boolean deleteCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
