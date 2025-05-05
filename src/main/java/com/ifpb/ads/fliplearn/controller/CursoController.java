package com.ifpb.ads.fliplearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.ads.fliplearn.model.Curso;
import com.ifpb.ads.fliplearn.service.CursoService;

@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public void save(@RequestBody Curso curso){
        service.save(curso);
    }
    
    @GetMapping
    public List<Curso> getAll(){
        return service.getAll();
    }

}
