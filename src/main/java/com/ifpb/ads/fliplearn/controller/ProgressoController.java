package com.ifpb.ads.fliplearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.ads.fliplearn.model.Progresso;
import com.ifpb.ads.fliplearn.service.ProgressoService;

@RestController
@RequestMapping("progressos")
public class ProgressoController {


    @Autowired
    private ProgressoService service;

    @PostMapping
    public void save(@RequestBody Progresso progresso){
        service.save(progresso);
    }

    @GetMapping
    public List<Progresso> getAll(){
        return service.findAll();
    }
}
