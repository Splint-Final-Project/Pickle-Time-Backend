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
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import pickle_time.pickle_time.global.auth.jwt.TokenProvider;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private static AntPathMatcher matcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            String token = request.getHeader("Authorization");
            if (tokenProvider.validateToken(token)) {

                Authentication authentication = tokenProvider.getAuthentication(token);
                //            log.info(name);
                //            log.info(userDetails.getUsername());
                //            log.info((userDetails.getAuthorities().stream().findFirst()).get().getAuthority());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        filterChain.doFilter(request, response);

    }
}
