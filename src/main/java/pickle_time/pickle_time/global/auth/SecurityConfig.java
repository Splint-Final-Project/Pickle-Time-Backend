package pickle_time.pickle_time.global.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import pickle_time.pickle_time.global.auth.filter.JwtFilter;
import pickle_time.pickle_time.global.auth.oauth.OAuth2SuccessHandler;
import pickle_time.pickle_time.global.auth.oauth.UserOAuth2UserService;
import pickle_time.pickle_time.global.auth.jwt.JwtAuthenticationEntryPoint;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserOAuth2UserService userOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtFilter jwtFilter;

    private static final String[] PERMIT_URL = {


            "/", "/oauth2/authorization/**",  "api/v1/user/join", "/signup", "/auth/success", "api/v1/user/login",
            "/verify_iamport/*", "/api-docs/**", "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**",
    };

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
        return web -> web.ignoring()
                // error endpoint를 열어줘야 함, favicon.ico 추가!
                .requestMatchers("/error", "/favicon.ico");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // cors : 추후에는 밑에 주석에 있는 것을 활성화 해야 합니다.
//                .cors(AbstractHttpConfigurer::disable)
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                })))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 현재는 모든 요청에 대해 허용. -> 추후 로그인 시 수정
                .authorizeHttpRequests(request ->
                        request.requestMatchers(PERMIT_URL).permitAll()
                                .anyRequest().authenticated()
                )


                .oauth2Login(oauth ->
                        oauth.userInfoEndpoint(c -> c.userService(userOAuth2UserService))
                                .successHandler(oAuth2SuccessHandler)
                                
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exceptions) -> exceptions
                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                        );


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
