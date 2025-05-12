package com.ifpb.ads.fliplearn.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ads.fliplearn.entity.Progresso;
import com.ifpb.ads.fliplearn.repository.ProgressoRepository;

@Service

public class ProgressoService {

    @Autowired
    private ProgressoRepository repository;

   
    public Progresso save(Progresso progresso){
        return repository.save(progresso);
    }

    
    public List<Progresso> findAll(){
        Iterable<Progresso> progressos = repository.findAll();
        List<Progresso> progressos2 = StreamSupport
            .stream(progressos.spliterator(), false)
            .collect(Collectors.toList());

        return progressos2;
    }


    public Progresso update(Long id, Progresso progresso) {
        if (id.equals(progresso.getId())) {
            return this.repository.save(progresso);
        } else {
            throw new IllegalArgumentException("ID do progresso não corresponde ao ID fornecido.");
        }
    }


    public void delete(Long id) {
        this.repository.deleteById(id);
    }


    public Progresso getById(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Progresso não encontrado com id: " + id));
    }

    public void recalcularProgresso(Double cargaHorariaCompletada, Progresso progresso){
        Double cargaHorariaTotal = progresso.getCargaHorariaDoCurso();
        Double porcentagem = (cargaHorariaCompletada / cargaHorariaTotal) * 100;

        progresso.setPercentualConcluido(porcentagem);

        this.update(progresso.getId(), progresso);
    }

}
