package com.ifpb.ads.fliplearn.repository;

import com.ifpb.ads.fliplearn.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Buscar categorias que não têm categoria pai (principais)
    List<Categoria> findByCategoriaPaiIsNull();

    // Buscar subcategorias de uma categoria específica
    List<Categoria> findByCategoriaPaiId(Long categoriaPaiId);
}
