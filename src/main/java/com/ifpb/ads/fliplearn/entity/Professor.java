package com.ifpb.ads.fliplearn.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeProfessor", nullable = false)
    private String nome;

    @Column(name = "emailProfessor", nullable = false)
    private String email;

    @Column(name = "dataDeNascimentoProfessor", nullable = false)
    private Date dataDeNascimento;

    //@OneToMany(mappedBy = "professor")
    //private List<Curso> cursos
}
