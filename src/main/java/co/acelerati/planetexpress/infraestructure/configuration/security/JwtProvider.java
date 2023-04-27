package co.acelerati.planetexpress.infraestructure.configuration.security;

import co.acelerati.planetexpress.domain.exception.ExpiredTokenException;
import co.acelerati.planetexpress.domain.exception.MalformedTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String getUserNameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().get("name", String.class);
    }

    public String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    private Key getKey() {
        byte[] keyBytes = Base64.getMimeDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            logger.info("Parsing token");
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().toString();
            return true;
        } catch (MalformedJwtException exception) {
            logger.error("Malformed token");
            throw new MalformedTokenException(exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            logger.error("Token not supported");
            throw new UnsupportedJwtException(exception.getMessage());
        } catch (ExpiredJwtException exception) {
            logger.error("The token expired");
            throw new ExpiredTokenException(exception.getMessage());
        } catch (IllegalArgumentException exception) {
            logger.error("The token is empty");
            throw new IllegalArgumentException(exception.getMessage());
        } catch (SignatureException exception) {
            logger.warn("We can't verify the signature");
        }
        return false;
    }
}
