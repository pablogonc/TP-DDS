package ddsutn.Servicios;

import ddsutn.Business.Persona.Rescatista;
import ddsutn.Repositorio.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RescatistaSvc {

    @Autowired
    private PersonaRepo personaRepo;

    public void save(Rescatista rescatista) {
        personaRepo.save(rescatista);
    }

}
