package com.ifpb.ads.fliplearn.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    private Questionario questionario;
}
