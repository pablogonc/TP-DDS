package ddsutn.Repositorio;

import ddsutn.Business.Mascota.Foto.Foto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepo extends CrudRepository<Foto, Long> {

}
