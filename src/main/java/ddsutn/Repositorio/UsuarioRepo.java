package ddsutn.Repositorio;

import ddsutn.Seguridad.Usuario.Administrador;
import ddsutn.Seguridad.Usuario.StandardUser;
import ddsutn.Seguridad.Usuario.Usuario;
import ddsutn.Seguridad.Usuario.Voluntario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);

    @Query(value = "SELECT * FROM usuario WHERE usuario = :username AND tipo_usuario = 'Administrador'", nativeQuery = true)
    Optional<Administrador> findAdminByUsuario(@Param("username") String username);

    @Query(value = "SELECT * FROM usuario WHERE usuario = :username AND tipo_usuario = 'StandardUser'", nativeQuery = true)
    Optional<StandardUser> findStandardByUsuario(@Param("username") String username);

    @Query(value = "SELECT * FROM usuario WHERE usuario = :username AND tipo_usuario = 'Voluntario'", nativeQuery = true)
    Optional<Voluntario> findVoluntarioByUsuario(@Param("username") String username);

}
