package ddsutn.Repositorio;

import ddsutn.Business.Publicacion.Comodidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComodidadRepo extends CrudRepository<Comodidad, Long> {

}
