package com.ifpb.ads.fliplearn.dto;

import java.util.Date;

public record ProfessorDTO(
        Long idUsuario,
        String nome,
        String login,
        Date dataDeNascimento
        //List<Curso> cursos
        // List<Curso> cursos
) {}

