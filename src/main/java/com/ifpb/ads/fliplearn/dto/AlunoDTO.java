package com.ifpb.ads.fliplearn.dto;

import java.util.Date;

public record AlunoDTO(
        Long idUsuario,
        String nome,
        String login,
        Date dataDeNascimento,
        String graduacao
        //List<Curso> cursos
) {}

