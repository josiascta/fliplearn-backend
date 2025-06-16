package com.ifpb.ads.fliplearn.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

    private Long idUsuario;

    private String nome;

    private String sobrenome;

    private String login;

    private List<String> cargos;
}
