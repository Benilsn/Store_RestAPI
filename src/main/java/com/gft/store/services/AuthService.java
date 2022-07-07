package com.gft.store.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gft.store.models.dtos.TokenDTO;
import com.gft.store.models.entities.UserModel;
import com.gft.store.models.forms.LoginForm;

@Service
public class AuthService {

    // @Autowired
    // private AuthenticationManager manager;

    @Value("${store.jwt.secret}")
    private String secret;

    @Value("${store.jwt.expiration}")
    private String expiration;

    @Value("${store.jwt.issuer}")
    private String issuer;

    public TokenDTO authenticate(LoginForm loginForm) {

        // var auth = manager
        // .authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(),
        // loginForm.getPassword()));
        String token = generateToken(null);

        return new TokenDTO(token);
    }

    private Algorithm createAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    private String generateToken(Authentication auth) {
        UserModel user = (UserModel) auth.getPrincipal();
        Date today = new Date();
        Date expTime = new Date(today.getTime() + Long.parseLong(expiration));

        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(expTime)
                .withSubject(user.getId().toString())
                .sign(createAlgorithm());
    }

    public boolean checkToken(String token) {
        if (token.equals(null)) {
            return false;
        }

        try {
            JWT.require(createAlgorithm()).withIssuer(issuer).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public Long returnUserId(String token) {
        var userId = JWT.require(createAlgorithm()).withIssuer(issuer).build().verify(token).getSubject();

        return Long.parseLong(userId);
    }
}
