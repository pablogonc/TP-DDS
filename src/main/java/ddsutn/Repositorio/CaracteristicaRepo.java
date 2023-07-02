package ddsutn.Repositorio;

import ddsutn.Business.Mascota.Caracteristica.Caracteristica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicaRepo extends CrudRepository<Caracteristica, Long> {

}
