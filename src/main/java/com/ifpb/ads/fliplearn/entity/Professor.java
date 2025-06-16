package com.ifpb.ads.fliplearn.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "professor")
public class Professor extends User{

    @Column(name = "dataDeNascimentoProfessor", nullable = false)
    private Date dataDeNascimento;

    @OneToMany(mappedBy = "professor")
    private List<Curso> cursos;
}
