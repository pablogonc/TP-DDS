package ddsutn.Repositorio;

import ddsutn.Business.Persona.Contacto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepo extends CrudRepository<Contacto, Long> {

}
