package com.ifpb.ads.fliplearn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.ads.fliplearn.entity.Categoria;
import com.ifpb.ads.fliplearn.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

   
    private final CategoriaRepository categoriaRepository;

    // Criar uma nova categoria
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaRepository.save(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    // Buscar todas as categorias principais (sem categoria pai)
    @GetMapping
    public List<Categoria> getCategorias() {
        return categoriaRepository.findByCategoriaPaiIsNull();
    }

    // Buscar subcategorias de uma categoria
    @GetMapping("/subcategorias/{categoriaPaiId}")
    public List<Categoria> getSubcategorias(@PathVariable Long categoriaPaiId) {
        return categoriaRepository.findByCategoriaPaiId(categoriaPaiId);
    }

    // Buscar uma categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar uma categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        Categoria categoriaAtualizada = categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    // Deletar uma categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
