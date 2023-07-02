package ddsutn.Servicios;

import ddsutn.Business.Persona.Duenio;
import ddsutn.Repositorio.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DuenioSvc {

    @Autowired
    private PersonaRepo personaRepo;

    public void save(Duenio duenio) {
        personaRepo.save(duenio);
    }

}
