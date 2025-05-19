package com.ifpb.ads.fliplearn.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ads.fliplearn.entity.Questao;
import com.ifpb.ads.fliplearn.repository.QuestaoRepository;

@Service
public class QuestaoService {

    @Autowired
    private QuestaoRepository repository;

    public Questao saveQuestao(Questao questao){
        return repository.save(questao);
    }

    public List<Questao> findAll(){
        Iterable<Questao> questaos = repository.findAll();
        List<Questao> questaos2 = StreamSupport
            .stream(questaos.spliterator(), false)
            .collect(Collectors.toList());

        return questaos2;
    }

    public Questao update(Long id, Questao questao) {
        if (id.equals(questao.getId())) {
            return this.repository.save(questao);
        } else {
            throw new IllegalArgumentException("ID da Questão não corresponde ao ID fornecido.");
        }
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
    
    public Questao getById(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Questão não encontrado com id: " + id));
    }

}
