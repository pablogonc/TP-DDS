package ddsutn.Repositorio;

import ddsutn.Business.Publicacion.Pregunta.Pregunta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepo extends CrudRepository<Pregunta, Long> {

	@Query(value = "SELECT * FROM pregunta WHERE id_organizacion IS NULL", nativeQuery = true)
	List<Pregunta> findAllGlobales();

}
