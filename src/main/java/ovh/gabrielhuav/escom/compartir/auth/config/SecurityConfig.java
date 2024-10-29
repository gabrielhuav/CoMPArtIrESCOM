package ovh.gabrielhuav.escom.compartir.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Desactiva CSRF para permitir solicitudes desde aplicaciones móviles
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login", "/user/register", "/register").permitAll()  // Permitir el acceso al endpoint de login y registro
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/home").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic();  // Usa autenticación HTTP básica para endpoints de API (sin redirección HTML)

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
