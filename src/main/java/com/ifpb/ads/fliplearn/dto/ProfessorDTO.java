package com.ifpb.ads.fliplearn.dto;

import java.util.Date;

public record ProfessorDTO(
        Long id,
        String nome,
        String email,
        Date dataDeNascimento
        // List<Curso> cursos
) {}

