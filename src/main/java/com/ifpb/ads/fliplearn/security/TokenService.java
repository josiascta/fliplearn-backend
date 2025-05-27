package com.ifpb.ads.fliplearn.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ifpb.ads.fliplearn.dto.LoginDTO;
import com.ifpb.ads.fliplearn.entity.Role;
import com.ifpb.ads.fliplearn.entity.User;
import com.ifpb.ads.fliplearn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${security.token.secret}")
    private String secretKey;

    public String gerarToken(LoginDTO loginDTO) throws AuthenticationException {
        User user = userRepository.findByLogin(loginDTO.getLogin()).orElseThrow(
                    () -> new AuthenticationException("Usuário não disponível"));

        boolean eIgual = passwordEncoder.matches(loginDTO.getSenha(), user.getSenha());

        if(!eIgual) {
            throw new AuthenticationException("Senha ou login errados!");
        }

        Algorithm algorithm =  Algorithm.HMAC256(secretKey);

        List<String> roles = user.getCargos().stream()
                .map(Role::getAuthority)
                .collect(Collectors.toList());

        String token = JWT.create().withIssuer("bookify")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getIdUsuario().toString())
                .withClaim("roles", roles)
                .sign(algorithm);
        return token;
    }

}
