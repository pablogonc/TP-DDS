package ddsutn.Servicios;

import ddsutn.Business.Persona.Persona;
import ddsutn.Repositorio.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaSvc {

    @Autowired
    private PersonaRepo personaRepo;

    public void save(Persona persona) {
        personaRepo.save(persona);
    }

}
