package com.ifpb.ads.fliplearn.controller;

import com.ifpb.ads.fliplearn.dto.*;
import com.ifpb.ads.fliplearn.entity.User;
import com.ifpb.ads.fliplearn.exception.RegraDeNegocioException;
import com.ifpb.ads.fliplearn.security.TokenService;
import com.ifpb.ads.fliplearn.service.AlunoService;
import com.ifpb.ads.fliplearn.service.ProfessorService;
import com.ifpb.ads.fliplearn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AlunoService alunoService;
    private final ProfessorService professorService;
    private final TokenService tokenService;


    @PostMapping("/register")
    public User cadastrarUser(@RequestBody UserCreateDTO user) throws RegraDeNegocioException {
        return userService.cadastrarUsuario(user);
    }

    @PostMapping("/registerAluno")
    public AlunoDTO cadastrarUserAluno(@RequestBody AlunoCreateDTO user) throws RegraDeNegocioException {
        return alunoService.create(user);
    }

    @PostMapping("/registerProfessor")
    public ProfessorDTO cadastrarUserProfessor(@RequestBody ProfessorCreateDTO user) throws RegraDeNegocioException {
        return professorService.create(user);
    }

    @GetMapping
    public Optional<User> findByLoginAndSenha(@RequestParam String login,
                                              @RequestParam String senha){
        return userService.findByLoginAndSenha(login, senha);
    }

    @PostMapping("/token")
    public String createToken(@RequestBody LoginDTO loginDTO) throws AuthenticationException {
        return tokenService.gerarToken(loginDTO);
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> findById(@PathVariable Long id){
        return userService.findById(id);
    }
}
