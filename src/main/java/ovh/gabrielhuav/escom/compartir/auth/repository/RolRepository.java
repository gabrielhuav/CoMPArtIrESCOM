package ovh.gabrielhuav.escom.compartir.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ovh.gabrielhuav.escom.compartir.auth.model.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
