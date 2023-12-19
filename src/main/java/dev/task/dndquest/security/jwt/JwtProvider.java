package dev.task.dndquest.security.jwt;

import dev.task.dndquest.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class JwtProvider {
    private static final String JWT_REQ_HEADER_NAME = "Authorization";
    private static final String JWT_REQ_START = "Bearer ";
    private static final int JWT_REQ_START_POSITION = 7;
    private final UserDetailsService userDetailsService;
    private final JwtProperties jwtProperties;

    public String resolveToToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(JWT_REQ_HEADER_NAME);
        if (bearerToken != null && bearerToken.startsWith(JWT_REQ_START)) {
            return bearerToken.substring(JWT_REQ_START_POSITION);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails =
                this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String generateToken(String login) {
        Claims claims = Jwts.claims().subject(login).build();
        Date issuedDate = new Date();
        Date validity = new Date(issuedDate.getTime() + jwtProperties.validityTime());
        return Jwts.builder()
                .claims()
                .add(claims)
                .issuedAt(new Date())
                .expiration(validity)
                .and()
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return !claimsJws
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException();
        }
    }

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secretKey()));
    }
}
