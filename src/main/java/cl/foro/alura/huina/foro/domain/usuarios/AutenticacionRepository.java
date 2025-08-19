package cl.foro.alura.huina.foro.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AutenticacionRepository extends JpaRepository<Autentificacion, Long> {
    UserDetails findByLogin(String login);
}
