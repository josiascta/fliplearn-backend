package com.ifpb.ads.fliplearn.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ads.fliplearn.entity.Curso;
import com.ifpb.ads.fliplearn.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public CursoService(CursoRepository repository2) {
        //TODO Auto-generated constructor stub
        repository = repository2;
    }

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

    public Curso update(Long id, Curso curso) {
        if (id.equals(curso.getId())) {
            return this.repository.save(curso);
        } else {
            throw new IllegalArgumentException("ID do curso não corresponde ao ID fornecido.");
        }
    }

    public Curso findCursoById(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + id));
    }

    public void deleteCourse(Long id){
        this.repository.deleteById(id);
    }
    
}
