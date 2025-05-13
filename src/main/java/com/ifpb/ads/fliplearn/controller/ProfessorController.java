package com.ifpb.ads.fliplearn.controller;

import com.ifpb.ads.fliplearn.dto.ProfessorCreateDTO;
import com.ifpb.ads.fliplearn.dto.ProfessorDTO;
import com.ifpb.ads.fliplearn.entity.Professor;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import com.ifpb.ads.fliplearn.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll(){
        return new ResponseEntity<>(professorService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable Long id,
                                               @RequestBody ProfessorCreateDTO professorCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(professorService.update(professorCreateDTO, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorCreateDTO professorCreateDTO) {
        return new ResponseEntity<>(professorService.create(professorCreateDTO), HttpStatus.OK);
    }

}
