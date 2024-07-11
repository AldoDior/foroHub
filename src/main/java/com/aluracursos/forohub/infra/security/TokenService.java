package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.user.User;
import com.aluracursos.forohub.domain.user.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jdk.internal.org.jline.reader.LineReaderBuilder;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.stream.DoubleStream;

import static java.util.stream.DoubleStream.*;

@Service
@Service
public class TokenService {

    @Value("${forum.jwt.secret}")
    private String secret;
    private LineReaderBuilder Jwts;

    public TokenService(LineReaderBuilder jwts) {
        Jwts = jwts;
    }

    public String generateToken(Authentication authentication) {
        Class<Authentication> user = Authentication.class;
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 86400000);

        DoubleStream Jwts = empty();
        return builder().toString();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public Object getSubject(String token) {
        return null;
    }
}
