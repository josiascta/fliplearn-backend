package com.ifpb.ads.fliplearn.dto;

import java.util.Date;
import java.util.List;

import com.ifpb.ads.fliplearn.entity.Curso;

public record AlunoDTO(
        Long idUsuario,
        String nome,
        String login,
        Date dataDeNascimento,
        String graduacao,
        List<Curso> cursos
) {}

