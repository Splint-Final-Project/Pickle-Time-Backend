package pickle_time.pickle_time.global.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import pickle_time.pickle_time.global.auth.exception.JwtAuthenticationException;
import pickle_time.pickle_time.global.auth.jwt.TokenProvider;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private static AntPathMatcher matcher = new AntPathMatcher();
    private static final String[] PERMIT_URL = {
            "/", "/oauth2/authorization/**",  "api/v1/user/join", "api/v1/user/login"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {


            String token = request.getHeader("Authorization");
            if (!tokenProvider.validateToken(token)) throw new JwtAuthenticationException("Invalid token");
            log.info("TokenFilter Token : " , token);

            Authentication authentication = tokenProvider.getAuthentication(token);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String name = authentication.getName();


            log.info(name);
            log.info(userDetails.getUsername());
            log.info((userDetails.getAuthorities().stream().findFirst()).get().getAuthority());






            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (JwtAuthenticationException e) {
            log.info(e.getMessage());
            log.info("TokenFilter Error (403) ");
        }

        filterChain.doFilter(request, response);

    }
}
