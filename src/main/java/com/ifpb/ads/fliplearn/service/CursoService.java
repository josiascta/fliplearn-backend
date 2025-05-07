package com.ifpb.ads.fliplearn.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ads.fliplearn.model.Curso;
import com.ifpb.ads.fliplearn.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public Curso save(Curso curso){
        return this.repository.save(curso);
    }

    public List<Curso> getAll(){
        Iterable<Curso> cursosIterable = repository.findAll();
        List<Curso> cursos = StreamSupport
            .stream(cursosIterable.spliterator(), false)
            .collect(Collectors.toList());
        return cursos;
    }

    public void update(Long id, Curso curso) {
        // TODO Auto-generated method stub
        if(id == curso.getId())
            this.repository.save(curso);
    }

    public Curso findCursoById(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + id));
    }

    public void deleteCourse(Long id){
        this.repository.deleteById(id);
    }
    
}
