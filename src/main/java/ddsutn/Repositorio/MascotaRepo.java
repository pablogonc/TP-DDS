package ddsutn.Repositorio;

import ddsutn.Business.Mascota.Mascota;
import ddsutn.Seguridad.Usuario.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MascotaRepo extends CrudRepository<Mascota, Long> {

    @Query(value = "SELECT * FROM mascota WHERE id_qr = :qr", nativeQuery = true)
    Optional<Mascota> findById_QR(@Param("qr") String qr);

}
