package com.ifpb.ads.fliplearn.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_questao")
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String enunciado;

    @ElementCollection
    @CollectionTable(name = "tb_alternativas", joinColumns = @JoinColumn(name = "questao_id"))
    @Column(name = "alternativa")
    private List<String> alternativas;

    @Column(nullable = false)
    private String respostaCorreta;

    @Column
    private String tipo; // Ex: "multipla_escolha", "dissertativa"

    // @ManyToOne
    // private Questionario questionario;
}
