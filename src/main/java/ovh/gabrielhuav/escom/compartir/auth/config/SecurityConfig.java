package ovh.gabrielhuav.escom.compartir.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Deshabilitar CSRF para simplificar pruebas
            .authorizeHttpRequests()
                .requestMatchers("/auth/**", "/login", "/home").permitAll()  // Permitir acceso sin autenticación a estas rutas
                .requestMatchers("/imagenes/**", "/css/**", "/js/**", "/webjars/**").permitAll()  // Permitir acceso a recursos estáticos
                .anyRequest().authenticated()  // Proteger todas las demás rutas
            .and()
            .logout()
                .logoutUrl("/logout")  // URL para deslogueo
                .logoutSuccessUrl("/auth/login")  // Redirige al login después del logout
                .permitAll();

        return http.build();
    }
}
