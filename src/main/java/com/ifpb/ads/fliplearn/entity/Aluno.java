package com.ifpb.ads.fliplearn.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToMany
    private List<Curso> cursos;
}
