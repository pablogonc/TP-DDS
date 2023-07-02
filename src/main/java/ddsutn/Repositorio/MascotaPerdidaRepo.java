package ddsutn.Repositorio;

import ddsutn.Business.Mascota.MascotaPerdida;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaPerdidaRepo extends CrudRepository<MascotaPerdida, Long> {

}
