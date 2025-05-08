package com.ifpb.ads.fliplearn.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_progresso")
public class Progresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double percentualConcluido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimaAtividade;

    @Column
    private Double notaMedia;

    @Column(length = 500)
    private String feedback;

    @OneToOne
    private Curso curso;

    // @ManyToOne
    // private Aluno aluno;
    
}
