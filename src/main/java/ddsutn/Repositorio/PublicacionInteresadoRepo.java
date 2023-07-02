package ddsutn.Repositorio;

import ddsutn.Business.Publicacion.PublicacionInteresado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicacionInteresadoRepo extends CrudRepository<PublicacionInteresado, Long> {

	@Query(value = "SELECT * FROM publicacion_interesado WHERE cod_baja = :codigo", nativeQuery = true)
	Optional<PublicacionInteresado> findByCod_Baja(@Param("codigo") String codigo);

}
