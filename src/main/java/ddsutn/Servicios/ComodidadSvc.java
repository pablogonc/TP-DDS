package ddsutn.Servicios;

import ddsutn.Business.Publicacion.Comodidad;
import ddsutn.Repositorio.ComodidadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComodidadSvc {

    @Autowired
    ComodidadRepo comodidadRepo;

    public List<Comodidad> findAll() {
        return (List<Comodidad>) comodidadRepo.findAll();
    }

}
