package com.ifpb.ads.fliplearn.controller;

import com.ifpb.ads.fliplearn.entity.Questionario;
import com.ifpb.ads.fliplearn.repository.QuestionarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questionarios")
@RequiredArgsConstructor
public class QuestionarioController {

   
    private final QuestionarioRepository questionarioRepository;

    // Criar um novo questionário
    @PostMapping
    public ResponseEntity<Questionario> createQuestionario(@RequestBody Questionario questionario) {
        Questionario novoQuestionario = questionarioRepository.save(questionario);
        return new ResponseEntity<>(novoQuestionario, HttpStatus.CREATED);
    }

    // Buscar todos os questionários de uma categoria
    @GetMapping("/categoria/{categoriaId}")
    public List<Questionario> getQuestionariosByCategoria(@PathVariable Long categoriaId) {
        return questionarioRepository.findByCategoriaId(categoriaId);
    }

    // Buscar um questionário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Questionario> getQuestionarioById(@PathVariable Long id) {
        Optional<Questionario> questionario = questionarioRepository.findById(id);
        return questionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um questionário
    @PutMapping("/{id}")
    public ResponseEntity<Questionario> updateQuestionario(@PathVariable Long id, @RequestBody Questionario questionario) {
        if (!questionarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        questionario.setId(id);
        Questionario questionarioAtualizado = questionarioRepository.save(questionario);
        return ResponseEntity.ok(questionarioAtualizado);
    }

    // Deletar um questionário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionario(@PathVariable Long id) {
        if (!questionarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        questionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
