package com.ifpb.ads.fliplearn.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifpb.ads.fliplearn.dto.UserCreateDTO;
import com.ifpb.ads.fliplearn.dto.UserDTO;
import com.ifpb.ads.fliplearn.entity.Role;
import com.ifpb.ads.fliplearn.entity.User;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import com.ifpb.ads.fliplearn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final RoleService cargoService;

    public User cadastrarUsuario(UserCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {

        User usuarioEntity = objectMapper.convertValue(usuarioCreateDTO, User.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        usuarioEntity.setSenha(bCryptPasswordEncoder.encode(usuarioEntity.getPassword()));
        Set<Role> cargoEntitySet = new HashSet<>();

        for(Integer i : usuarioCreateDTO.getCargos()){
            cargoEntitySet.add(cargoService.findById(i));
        }
        usuarioEntity.setCargos(cargoEntitySet);
        usuarioRepository.save(usuarioEntity);
        return usuarioEntity;
    }

    public Optional<UserDTO> findById(Long id) {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<String> cargos = new ArrayList<>();
        for(Role r : user.getCargos()){
          String cargo = r.getNome().replace("ROLE_", "");

          cargos.add(cargo);
        }

        UserDTO userDTO = objectMapper.convertValue(user, UserDTO.class);
        userDTO.setCargos(cargos);

        return Optional.of(userDTO);
    }

    public Integer getIdLoggedUser() {
        Integer findUserId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findUserId;
    }

    public Optional<User> findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha);
    }

    public List<UserDTO> listarTodos() {
        List<User> usuarios = usuarioRepository.findAll();

        List<UserDTO> usuariosDTO = new ArrayList<>();
        for (User user : usuarios) {
            List<String> cargos = new ArrayList<>();
            for (Role r : user.getCargos()) {
                String cargo = r.getNome().replace("ROLE_", "");
                cargos.add(cargo);
            }
            UserDTO userDTO = objectMapper.convertValue(user, UserDTO.class);
            userDTO.setCargos(cargos);
            usuariosDTO.add(userDTO);
        }

        return usuariosDTO;
    }

    public void deletarUsuario(Long id) {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(user);
    }

}
