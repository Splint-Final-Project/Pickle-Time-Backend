package pickle_time.pickle_time.global.auth.filter;

import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pickle_time.pickle_time.global.auth.detail.PrincipalDetails;
import pickle_time.pickle_time.global.auth.exception.JwtAuthenticationException;
import pickle_time.pickle_time.global.jwt.TokenProvider;

import java.io.IOException;
import java.security.Principal;

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
            log.info("TokenFilter Token : " , token);

            Authentication authentication = tokenProvider.getAuthentication(token);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String name = authentication.getName();


            log.info(name);
            log.info(userDetails.getUsername());
            log.info((userDetails.getAuthorities().stream().findFirst()).get().getAuthority());






            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtAuthenticationException e) {
            log.info(e.getMessage());
            log.info("TokenFilter Error (403) ");
        }

        filterChain.doFilter(request, response);

    }
}
