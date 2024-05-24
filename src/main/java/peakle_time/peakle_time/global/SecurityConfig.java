package peakle_time.peakle_time.global;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     *
     */
    private static final String[] IGNORE_URL = {
            "/", "/oauth/**", "/signup", "/error", "/favicon.ico"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                        .formLogin(AbstractHttpConfigurer::disable);

        http
                .authorizeHttpRequests(
                        (request) -> request
                                .requestMatchers(IGNORE_URL).permitAll()
                                .anyRequest().authenticated()

                );

        return http.build();
    }
}
