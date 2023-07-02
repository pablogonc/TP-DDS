package ddsutn.Repositorio;

import ddsutn.Business.Organizacion.Organizacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizacionRepo extends CrudRepository<Organizacion, Long> {
}
