package com.ifpb.ads.fliplearn.dto;

import lombok.Getter;

import java.util.Date;
import java.util.List;

public record ProfessorCreateDTO(
        String login,
        String senha,
        String nome,
        String sobrenome,
        Date dataDeNascimento,
        @Getter List<Integer> cargos)
{}

