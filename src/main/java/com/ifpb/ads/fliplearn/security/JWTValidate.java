package com.ifpb.ads.fliplearn.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JWTValidate {

    @Value("${security.token.secret}")
    private String secretKey;

    public Map<String, Object> validateToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var verifier = JWT.require(algorithm).build().verify(token);
            String subject = verifier.getSubject();
            List<String> roles = verifier.getClaim("roles").asList(String.class);

            Map<String, Object> result = new HashMap<>();
            result.put("subject", subject);
            result.put("roles", roles);
            return result;
        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            return Collections.emptyMap();
        }
    }

}
