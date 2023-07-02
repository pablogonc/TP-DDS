package ddsutn.Repositorio;

import ddsutn.Business.Hogares.Ubicacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepo extends CrudRepository<Ubicacion, Long> {

}
