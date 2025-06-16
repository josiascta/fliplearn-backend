package com.ifpb.ads.fliplearn.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ifpb.ads.fliplearn.entity.Role;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import com.ifpb.ads.fliplearn.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository cargoRepository;


    public Role findById(Integer idCargo) throws RegraDeNegocioException {
        return cargoRepository.findById(idCargo).orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
    }

    public List<Role> findAll() throws RegraDeNegocioException {
        return cargoRepository.findAll();
    }

    public List<Role> findAllById(List<Integer> idCargo) {
        return cargoRepository.findAllById(idCargo);
    }

    public Role findByNome(String nome) throws RegraDeNegocioException {
        return cargoRepository.findByNome(nome)
                .orElseThrow(() -> new RegraDeNegocioException("Cargo não encontrado!"));
    }

}
