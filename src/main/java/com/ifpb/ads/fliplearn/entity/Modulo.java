package com.ifpb.ads.fliplearn.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_modulo", nullable = false)
    private String nome;

    @Column(name = "descricao_modulo")
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private String dataInicio;

    @Column(name = "data_fim", nullable = false)
    private String dataFim;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "modulo")
    private List<VideoAula> videoAulas;
    
    @OneToMany(mappedBy = "modulo")
    private List<Questionario> questionarios;

}
