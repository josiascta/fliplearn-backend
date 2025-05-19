package com.ifpb.ads.fliplearn.dto;

import java.util.Date;

public record ProfessorCreateDTO(Long id,
                                 String nome,
                                 String email,
                                 Date dataDeNascimento)
{}

