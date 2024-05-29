package pickle_time.pickle_time.global.jwt;




import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pickle_time.pickle_time.global.auth.dto.PrincipalDetails;
import pickle_time.pickle_time.global.auth.exception.JwtAuthenticationException;
import pickle_time.pickle_time.global.auth.service.UserDetailService;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {

    private static String key = "64461f01e1af406da538b9c48d801ce59142452199ff112fb5404c8e7e98e3ff";
    private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

    private final UserDetailService userDetailService;


    public String generateToken(Authentication authentication) {
        long now = (new Date()).getTime();

        String authorities = authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(","));

        Date accessTokenExpiresIn = new Date(now + 86400000);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        if(claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);

    }

    private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        return Collections.singletonList(new SimpleGrantedAuthority(
                claims.get("auth").toString()));
    }

    public boolean validateToken(String token) throws JwtAuthenticationException {

        if (token == null || token.length() == 0) {
            return false;
        }
        Claims claims = parseClaims(token);
        System.out.println(claims.get("auth").toString());
        return claims.getExpiration().after(new Date());
    }

    // accessToken
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
