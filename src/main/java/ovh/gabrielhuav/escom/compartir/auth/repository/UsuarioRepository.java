package ovh.gabrielhuav.escom.compartir.auth.repository;

import ovh.gabrielhuav.escom.compartir.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}
