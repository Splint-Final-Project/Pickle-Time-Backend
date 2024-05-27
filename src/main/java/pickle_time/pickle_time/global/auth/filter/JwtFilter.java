package pickle_time.pickle_time.global.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pickle_time.pickle_time.global.auth.exception.JwtAuthenticationException;
import pickle_time.pickle_time.global.jwt.TokenProvider;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        try {
            String token = request.getHeader("Authorization");
            if (!tokenProvider.validateToken(token)) throw new JwtAuthenticationException("Invalid token");
            log.info("TokenFilter Token : " + token);

            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (JwtAuthenticationException e) {
            log.info("TokenFilter Error (403) ");
        }

        filterChain.doFilter(request, response);

    }
}
