package com.ifpb.ads.fliplearn.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    
    @OneToMany(mappedBy = "categoriaPai", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria> subcategorias;

  
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;
}
