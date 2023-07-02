package ddsutn.Repositorio;

import ddsutn.Business.Publicacion.PublicacionDarEnAdopcion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionDarEnAdopcionRepo extends CrudRepository<PublicacionDarEnAdopcion, Long> {

}
