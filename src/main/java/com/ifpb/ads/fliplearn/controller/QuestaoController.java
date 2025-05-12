package com.ifpb.ads.fliplearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.ads.fliplearn.entity.Questao;
import com.ifpb.ads.fliplearn.service.QuestaoService;

@RestController
@RequestMapping("questoes")
public class QuestaoController {

    @Autowired
    private QuestaoService service;


    @PostMapping
    public Questao save(@RequestBody Questao questao){
        return service.saveQuestao(questao);
    }

    @GetMapping
    public List<Questao> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Questao getquestaoById(@PathVariable Long id){
        return this.service.getById(id);
    }

    @PutMapping("/{id}")
    public Questao update(@PathVariable Long id, @RequestBody Questao questao){
        return this.service.update(id, questao);
    }

    @DeleteMapping("/{id}")
    public void deletequestao(@PathVariable Long id){
        this.service.delete(id);
    }
}
