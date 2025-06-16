package com.ifpb.ads.fliplearn.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.ads.fliplearn.dto.AlunoCreateDTO;
import com.ifpb.ads.fliplearn.dto.AlunoDTO;
import com.ifpb.ads.fliplearn.service.AlunoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        return new ResponseEntity<>(alunoService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id,
                                           @RequestBody AlunoCreateDTO alunoCreateDTO) throws Exception {
        return new ResponseEntity<>(alunoService.update(alunoCreateDTO, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> create(@RequestBody AlunoCreateDTO alunoCreateDTO) throws Exception{
        return new ResponseEntity<>(alunoService.create(alunoCreateDTO), HttpStatus.OK);
    }
}
