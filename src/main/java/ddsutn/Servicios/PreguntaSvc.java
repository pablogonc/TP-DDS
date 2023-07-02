package ddsutn.Servicios;

import ddsutn.Business.Publicacion.Pregunta.Pregunta;
import ddsutn.Repositorio.PreguntaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PreguntaSvc {

    @Autowired
    PreguntaRepo preguntaRepo;

    public List<Pregunta> findAll() {
        return (List<Pregunta>) preguntaRepo.findAll();
    }

	public List<Pregunta> findAllGlobales() { return preguntaRepo.findAllGlobales(); }

}
