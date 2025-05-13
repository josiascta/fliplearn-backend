package com.ifpb.ads.fliplearn.dto;

import java.util.Date;

public record AlunoCreateDTO(
        Integer id,
        String nome,
        String email,
        Date dataDeNascimento,
        String graduacao
) {}
