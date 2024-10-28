package ovh.gabrielhuav.escom.compartir.auth.repository;

import ovh.gabrielhuav.escom.compartir.auth.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
