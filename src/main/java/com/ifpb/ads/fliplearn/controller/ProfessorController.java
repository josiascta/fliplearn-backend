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

import com.ifpb.ads.fliplearn.dto.ProfessorCreateDTO;
import com.ifpb.ads.fliplearn.dto.ProfessorDTO;
import com.ifpb.ads.fliplearn.service.ProfessorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
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
                                               @RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception {
        return new ResponseEntity<>(professorService.update(professorCreateDTO, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception {
        return new ResponseEntity<>(professorService.create(professorCreateDTO), HttpStatus.OK);
    }

}
