package com.ifpb.ads.fliplearn.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeCurso", nullable = false)
    private String nome;

    @Column(name = "descricaoCurso")
    private String descricao;

    @Column
    private Double cargaHoraria;

    // @ManyToOne
    // private Professor professor;
}