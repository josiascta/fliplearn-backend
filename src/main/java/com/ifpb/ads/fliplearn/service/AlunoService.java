package com.ifpb.ads.fliplearn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifpb.ads.fliplearn.dto.AlunoCreateDTO;
import com.ifpb.ads.fliplearn.dto.AlunoDTO;
import com.ifpb.ads.fliplearn.entity.Aluno;
import com.ifpb.ads.fliplearn.entity.Role;
import com.ifpb.ads.fliplearn.entity.User;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import com.ifpb.ads.fliplearn.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ObjectMapper objectMapper;
    private final RoleService cargoService;

    public AlunoDTO create(AlunoCreateDTO alunoCreateDTO) throws RegraDeNegocioException {
        Aluno usuarioEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        usuarioEntity.setSenha(bCryptPasswordEncoder.encode(usuarioEntity.getPassword()));
        Set<Role> cargoEntitySet = new HashSet<>();

        for(Integer i : alunoCreateDTO.getCargos()){
            cargoEntitySet.add(cargoService.findById(i));
        }
        usuarioEntity.setCargos(cargoEntitySet);

        alunoRepository.save(usuarioEntity);

        return convertToDTO(usuarioEntity);
    }


    public void delete(Long id) throws RegraDeNegocioException {
        Aluno aluno = getAluno(id);
        alunoRepository.delete(aluno);
    }

    public AlunoDTO update(AlunoCreateDTO alunoDTO, Long matricula) throws RegraDeNegocioException {
        Aluno aluno = getAluno(matricula);
        aluno.setNome(alunoDTO.nome());
        aluno.setDataDeNascimento(alunoDTO.dataDeNascimento());

        alunoRepository.save(aluno);

        return convertToDTO(aluno);
    }

    public AlunoDTO findById(Long id) throws RegraDeNegocioException {
        Aluno aluno = getAluno(id);
        return convertToDTO(aluno);
    }

    public List<AlunoDTO> findAll() {
        return alunoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private Aluno getAluno(Long id) throws RegraDeNegocioException {
        return alunoRepository.findAll().stream()
                .filter(aluno -> aluno.getIdUsuario().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Aluno não encontrado!"));
    }

    private AlunoDTO convertToDTO(Aluno aluno) {
        return objectMapper.convertValue(aluno, AlunoDTO.class);
    }
}

