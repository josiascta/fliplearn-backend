package com.ifpb.ads.fliplearn.dto;

import java.util.Date;

public record AlunoDTO(
        Integer id,
        String nome,
        String email,
        Date dataDeNascimento,
        String graduacao
        //List<Curso> cursos
) {}

