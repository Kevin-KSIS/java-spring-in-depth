package lowk.learning.moneyMana.config;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lowk.learning.moneyMana.contanst.Constant;
import lowk.learning.moneyMana.model.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String JWT_SECRET;
    private final long JWT_EXPIRATION = 604800000L;
    @Autowired
    private InMemoryTokenRepositoryImpl inMemTokenRepository;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getSigFromToken(String jwtToken) {
        String[] token = jwtToken.split("\\.");
        return token[token.length - 1];
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return null;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserPrinciple userPrinciple) {
        Map<String, Object> claims = new HashMap<>();
        // set roles to jwt token
        String authorities = userPrinciple.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("roles", authorities);
        claims.put("sessionId", UUID.randomUUID());

        return doGenerateToken(claims, userPrinciple.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    }

    public Boolean validateToken(String token, UserPrinciple userPrinciple) {

        // Check token logged out?
        PersistentRememberMeToken tokenLoggedOut = inMemTokenRepository.getTokenForSeries(
                getSigFromToken(token)
        );
        if (tokenLoggedOut != null) {
            return false;
        }

        final String username = getUsernameFromToken(token);
        return (username.equals(userPrinciple.getUsername()) && !isTokenExpired(token));
    }

    public String parse(String bearerString){
        if (bearerString == null || !bearerString.startsWith("Bearer ")) {
            return "";
        }
        return bearerString.substring(7);
    }

}
