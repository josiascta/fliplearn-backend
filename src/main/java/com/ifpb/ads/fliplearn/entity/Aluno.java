package com.ifpb.ads.fliplearn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno extends User{

    @Column(name = "dataDeNascimentoAluno", nullable = false)
    private Date dataDeNascimento;

    @Column(name = "graduacao", nullable = false)
    private String graduacao;

    //@ManyToMany(mappedBy = "alunos")
    //private List<Curso> cursos
}
