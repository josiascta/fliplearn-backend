package com.ifpb.ads.fliplearn.controller;

import com.ifpb.ads.fliplearn.dto.AlunoCreateDTO;
import com.ifpb.ads.fliplearn.dto.AlunoDTO;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import com.ifpb.ads.fliplearn.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<AlunoDTO> create(@RequestBody AlunoCreateDTO alunoCreateDTO) {
        return new ResponseEntity<>(alunoService.create(alunoCreateDTO), HttpStatus.OK);
    }
}
