package com.ifpb.ads.fliplearn.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ifpb.ads.fliplearn.model.Progresso;
import com.ifpb.ads.fliplearn.repository.ProgressoRepository;

@Service

public class ProgressoService {

    @Autowired
    private ProgressoRepository repository;

   
    public void save(Progresso progresso){
        repository.save(progresso);
    }

    
    public List<Progresso> findAll(){
        Iterable<Progresso> progressos = repository.findAll();
        List<Progresso> progressos2 = StreamSupport
            .stream(progressos.spliterator(), false)
            .collect(Collectors.toList());

        return progressos2;
    }

}
