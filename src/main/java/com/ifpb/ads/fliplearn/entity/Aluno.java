package com.ifpb.ads.fliplearn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "aluno")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeAluno", nullable = false)
    private String nome;

    @Column(name = "emailAluno", nullable = false)
    private String email;

    @Column(name = "dataDeNascimentoAluno", nullable = false)
    private Date dataDeNascimento;

    @Column(name = "graduacao", nullable = false)
    private String graduacao;

    //@ManyToMany(mappedBy = "alunos")
    //private List<Curso> cursos
}
