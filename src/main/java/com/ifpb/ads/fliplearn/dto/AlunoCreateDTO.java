package com.ifpb.ads.fliplearn.dto;

import lombok.Getter;

import java.util.Date;
import java.util.List;


public record AlunoCreateDTO(
        String login,
        String senha,
        String nome,
        String sobrenome,
        Date dataDeNascimento,
        String graduacao,
        @Getter List<Integer> cargos
) {}
