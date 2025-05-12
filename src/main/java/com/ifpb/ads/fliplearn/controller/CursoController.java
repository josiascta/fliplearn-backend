package com.ifpb.ads.fliplearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ifpb.ads.fliplearn.entity.Curso;
import com.ifpb.ads.fliplearn.service.CursoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public Curso save(@RequestBody Curso curso){
        return service.save(curso);
    }
    
    @GetMapping
    public List<Curso> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Curso getCursoById(@PathVariable Long id) {
        return service.findCursoById(id);
    }
    

    @PutMapping("/{id}")
    public Curso putCurso(@PathVariable Long id, @RequestBody Curso curso) {
        //TODO: process PUT request
        return service.update(id, curso);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
    }
    

}
