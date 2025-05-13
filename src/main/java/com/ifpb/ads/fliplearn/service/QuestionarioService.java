package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.entity.Questionario;
import com.ifpb.ads.fliplearn.repository.QuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionarioService {

    @Autowired
    private QuestionarioRepository questionarioRepository;

    // Criar um novo questionário
    public Questionario createQuestionario(Questionario questionario) {
        return questionarioRepository.save(questionario);
    }

    // Buscar todos os questionários de uma categoria específica
    public List<Questionario> getQuestionariosByCategoria(Long categoriaId) {
        return questionarioRepository.findByCategoriaId(categoriaId);
    }

    // Buscar um questionário por ID
    public Optional<Questionario> getQuestionarioById(Long id) {
        return questionarioRepository.findById(id);
    }

    // Atualizar um questionário
    public Questionario updateQuestionario(Long id, Questionario questionario) {
        if (questionarioRepository.existsById(id)) {
            questionario.setId(id);
            return questionarioRepository.save(questionario);
        }
        return null;
    }

    // Deletar um questionário
    public boolean deleteQuestionario(Long id) {
        if (questionarioRepository.existsById(id)) {
            questionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
