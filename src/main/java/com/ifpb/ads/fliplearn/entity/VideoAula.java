package com.ifpb.ads.fliplearn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class VideoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private String duracao;

    @Column(nullable = false)
    private String dataPublicacao; 

    @Column(nullable = true)
    private String dataAtualizacao;
    
    @Column(nullable = false)
    private Long professorId;

    @ManyToOne
    @JoinColumn(name = "modulo_id", nullable = false)
    private Modulo modulo;


    public VideoAula(String titulo, String descricao, String url, String thumbnailUrl, String duracao, String dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.duracao = duracao;
        this.dataPublicacao = dataPublicacao;
    }

}
