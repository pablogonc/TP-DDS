package ddsutn.Repositorio;

import ddsutn.Business.Publicacion.PublicacionMascotaEncontrada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionMascotaEncontradaRepo extends CrudRepository<PublicacionMascotaEncontrada, Long> {

}
