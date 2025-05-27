package com.ifpb.ads.fliplearn.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class UserDTO {

    private Long idUsuario;

    private String nome;

    private String sobrenome;

    private String login;

    private List<String> cargos;
}
