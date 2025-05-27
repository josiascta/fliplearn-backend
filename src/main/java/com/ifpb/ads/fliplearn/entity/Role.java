package com.ifpb.ads.fliplearn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CARGO")
public class Role implements GrantedAuthority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_CARGO")
    private int idCargo;

    @Column(name = "NOME")
    private String nome;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USUARIO_CARGO",
            joinColumns = @JoinColumn(name = "ID_CARGO"),
            inverseJoinColumns = @JoinColumn(name = "ID_USUARIO")
    )
    private Set<User> usuarios;

    @Override
    public String getAuthority() {
        return nome;
    }
}