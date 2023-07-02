package ddsutn.Repositorio;

import ddsutn.Business.Persona.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends CrudRepository<Persona, Long> {

}
