package ovh.gabrielhuav.escom.compartir.auth.controller;

import ovh.gabrielhuav.escom.compartir.auth.model.Usuario;
import ovh.gabrielhuav.escom.compartir.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @PostMapping("/login-form")
    public String loginUserForm(@RequestParam String correo, @RequestParam String password) {
        Optional<Usuario> foundUser = usuarioRepository.findByCorreo(correo);
        
        if (foundUser.isPresent()) {
            Usuario usuario = foundUser.get();
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                System.out.println("Usuario autenticado correctamente.");

                // Autenticación manual
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    usuario.getCorreo(),
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );

                // Establecer el token de autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println("Contexto de seguridad actualizado: " + SecurityContextHolder.getContext().getAuthentication());

                return "redirect:/home";  // Redirige al home si el login es exitoso
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return "redirect:/auth/login?error=true";  // Si el login falla, muestra un mensaje de error
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Retorna la página de login en HTML
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";  // Página de inicio que debe mostrarse después del login
    }
}
