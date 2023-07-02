package ddsutn.Servicios;

import ddsutn.Business.Mascota.Caracteristica.Caracteristica;
import ddsutn.Repositorio.CaracteristicaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CaracteristicaSvc {

    @Autowired
    CaracteristicaRepo caracteristicaRepo;

    public List<Caracteristica> findAll() {
        return (List<Caracteristica>) caracteristicaRepo.findAll();
    }

}
