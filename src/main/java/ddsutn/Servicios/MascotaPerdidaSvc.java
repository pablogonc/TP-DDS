package ddsutn.Servicios;

import ddsutn.Business.Mascota.MascotaPerdida;
import ddsutn.Repositorio.MascotaPerdidaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MascotaPerdidaSvc {

    @Autowired
    private MascotaPerdidaRepo mascotaPerdidaRepo;

    public void save(MascotaPerdida mascotaPerdida) {
        mascotaPerdidaRepo.save(mascotaPerdida);
    }

}
