package ovh.gabrielhuav.escom.compartir.auth.repository;

import ovh.gabrielhuav.escom.compartir.auth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);  // EL campo es "nombre"
}
