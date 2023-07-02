package ddsutn.Repositorio;

import ddsutn.Business.Publicacion.Preferencias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciasRepo extends CrudRepository<Preferencias, Long> {

}
