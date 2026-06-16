package hospital.ms_user.repository;

import hospital.ms_user.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Spring Data JPA generará automáticamente la consulta SQL gracias a este nombre:
    boolean existsByCorreoInstitucional(String correoInstitucional);
}