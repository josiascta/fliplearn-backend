package com.ifpb.ads.fliplearn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifpb.ads.fliplearn.dto.AlunoCreateDTO;
import com.ifpb.ads.fliplearn.dto.AlunoDTO;
import com.ifpb.ads.fliplearn.entity.Aluno;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import com.ifpb.ads.fliplearn.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ObjectMapper objectMapper;

    public AlunoDTO create(AlunoCreateDTO alunoCreateDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoCreateDTO.nome());
        aluno.setEmail(alunoCreateDTO.email());
        aluno.setDataDeNascimento(alunoCreateDTO.dataDeNascimento());
        aluno.setGraduacao(alunoCreateDTO.graduacao());

        alunoRepository.save(aluno);

        return convertToDTO(aluno);
    }


    public void delete(Long id) throws RegraDeNegocioException {
        Aluno aluno = getAluno(id);
        alunoRepository.delete(aluno);
    }

    public AlunoDTO update(AlunoCreateDTO alunoDTO, Long matricula) throws RegraDeNegocioException {
        Aluno aluno = getAluno(matricula);
        aluno.setNome(alunoDTO.nome());
        aluno.setEmail(alunoDTO.email());
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
                .filter(aluno -> aluno.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Aluno não encontrado!"));
    }

    private AlunoDTO convertToDTO(Aluno aluno) {
        return objectMapper.convertValue(aluno, AlunoDTO.class);
    }
}

