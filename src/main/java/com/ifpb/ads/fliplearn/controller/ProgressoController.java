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

import com.ifpb.ads.fliplearn.entity.Progresso;
import com.ifpb.ads.fliplearn.service.ProgressoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("progressos")
@SecurityRequirement(name = "bearerAuth")
public class ProgressoController {


    @Autowired
    private ProgressoService service;

    @PostMapping
    public Progresso save(@RequestBody Progresso progresso){
        return service.save(progresso);
    }

    @GetMapping
    public List<Progresso> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Progresso getProgressoById(@PathVariable Long id){
        return this.service.getById(id);
    }

    @PutMapping("/{id}")
    public Progresso update(@PathVariable Long id, @RequestBody Progresso progresso){
        return this.service.update(id, progresso);
    }

    @DeleteMapping("/{id}")
    public void deleteProgresso(@PathVariable Long id){
        this.service.delete(id);
    }
}
