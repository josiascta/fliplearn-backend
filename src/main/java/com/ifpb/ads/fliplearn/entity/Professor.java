package com.ifpb.ads.fliplearn.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professor")
public class Professor extends User{

    @Column(name = "dataDeNascimentoProfessor", nullable = false)
    private Date dataDeNascimento;

    //@OneToMany(mappedBy = "professor")
    //private List<Curso> cursos
}
