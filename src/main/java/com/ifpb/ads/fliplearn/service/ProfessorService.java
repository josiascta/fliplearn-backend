package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.dto.ProfessorCreateDTO;
import com.ifpb.ads.fliplearn.dto.ProfessorDTO;
import com.ifpb.ads.fliplearn.entity.Professor;
import com.ifpb.ads.fliplearn.repository.ProfessorRepository;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;

    public ProfessorDTO create(ProfessorCreateDTO professorCreateDTO){
        Professor professor = new Professor();
        professor.setNome(professorCreateDTO.nome());
        professor.setEmail(professorCreateDTO.email());
        professor.setDataDeNascimento(professorCreateDTO.dataDeNascimento());

        professorRepository.save(professor);

        return convertToDTO(professor);
    }

    public void delete(Long id) throws RegraDeNegocioException {
        Professor professor = getProfessor(id);
        professorRepository.delete(professor);
    }

    public ProfessorDTO update(ProfessorCreateDTO professorDTO, Long matricula) throws RegraDeNegocioException {
        Professor professor = getProfessor(matricula);
        professor.setNome(professorDTO.nome());
        professor.setEmail(professorDTO.email());
        professor.setDataDeNascimento(professorDTO.dataDeNascimento());

        professorRepository.save(professor);

        return convertToDTO(professor);
    }

    public ProfessorDTO findById(Long id) throws RegraDeNegocioException {
        Professor professor = getProfessor(id);
        return convertToDTO(professor);
    }

    public List<ProfessorDTO> findAll(){
        return  professorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private Professor getProfessor(Long id) throws RegraDeNegocioException {
        return professorRepository.findAll().stream()
                .filter(professor -> professor.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Professor não encontrado!"));
    }

    private ProfessorDTO convertToDTO(Professor professor) {
        return objectMapper.convertValue(professor, ProfessorDTO.class);
    }
}
