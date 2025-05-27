package com.ifpb.ads.fliplearn.service;


import com.ifpb.ads.fliplearn.entity.Role;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import com.ifpb.ads.fliplearn.repository.RoleRepository;
import com.ifpb.ads.fliplearn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository cargoRepository;
    private final UserRepository usuarioRepository;

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
