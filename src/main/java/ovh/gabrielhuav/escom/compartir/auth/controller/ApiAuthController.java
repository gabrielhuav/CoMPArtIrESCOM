package ovh.gabrielhuav.escom.compartir.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ovh.gabrielhuav.escom.compartir.auth.entity.Usuario;
import ovh.gabrielhuav.escom.compartir.auth.repository.UsuarioRepository;

@RestController
public class ApiAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials credentials) {
        try {
            // Intenta autenticar al usuario con el AuthenticationManager
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getCorreo(), credentials.getPassword())
            );

            // Si la autenticación es exitosa, devuelve el estado 200
            return ResponseEntity.ok("Login successful");

        } catch (AuthenticationException e) {
            // Si la autenticación falla, devuelve el estado 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
    }
}