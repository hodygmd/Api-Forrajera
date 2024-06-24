package com.example.apiforrajera.config;

import com.example.apiforrajera.dto.EmpleadoDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider implements Serializable {
    @Value("${jwt.secret}")
    private String secret;
    private SecretKey key;

    @PostConstruct
    public void init() {
        System.out.println(secret);
        if (secret == null || secret.trim().isEmpty()) {
            // Genera una clave segura si el secreto está vacío
            key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        } else {
            // Si tienes un secreto existente, asegúrate de que sea seguro y lo suficientemente largo
            byte[] decodedKey = Base64.getDecoder().decode(secret);
            System.out.println("lenght: "+decodedKey.length);
            if (decodedKey.length < 32) {
                throw new IllegalArgumentException("La clave secreta debe tener al menos 256 bits (32 bytes) de longitud.");
            }
            key = Keys.hmacShaKeyFor(decodedKey);
        }
    }

    public String createToken(EmpleadoDto empleadoDto) {
        // Crear los claims con la información del empleado
        Map<String, Object> claims = Jwts.claims().setSubject(empleadoDto.getUsername());
        claims.put("clave", empleadoDto.getClave());

        // Establecer las fechas de emisión y expiración
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 3600 * 1000 * 24 * 10); // 10 días

        // Construir el token
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public void validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return "bad token";
        }
    }
}
