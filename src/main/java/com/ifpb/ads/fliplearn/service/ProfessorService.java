package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.dto.ProfessorCreateDTO;
import com.ifpb.ads.fliplearn.dto.ProfessorDTO;
import com.ifpb.ads.fliplearn.entity.Aluno;
import com.ifpb.ads.fliplearn.entity.Professor;
import com.ifpb.ads.fliplearn.entity.Role;
import com.ifpb.ads.fliplearn.repository.ProfessorRepository;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;
    private final RoleService cargoService;

    public ProfessorDTO create(ProfessorCreateDTO professorCreateDTO) throws RegraDeNegocioException {
        Professor usuarioEntity = objectMapper.convertValue(professorCreateDTO, Professor.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        usuarioEntity.setSenha(bCryptPasswordEncoder.encode(usuarioEntity.getPassword()));
        Set<Role> cargoEntitySet = new HashSet<>();

        for(Integer i : professorCreateDTO.getCargos()){
            cargoEntitySet.add(cargoService.findById(i));
        }
        usuarioEntity.setCargos(cargoEntitySet);

        professorRepository.save(usuarioEntity);

        return convertToDTO(usuarioEntity);
    }

    public void delete(Long id) throws RegraDeNegocioException {
        Professor professor = getProfessor(id);
        professorRepository.delete(professor);
    }

    public ProfessorDTO update(ProfessorCreateDTO professorDTO, Long matricula) throws RegraDeNegocioException {
        Professor professor = getProfessor(matricula);
        professor.setNome(professorDTO.nome());
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
                .filter(professor -> professor.getIdUsuario().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Professor não encontrado!"));
    }

    private ProfessorDTO convertToDTO(Professor professor) {
        return objectMapper.convertValue(professor, ProfessorDTO.class);
    }
}
