package ddsutn.Servicios;

import ddsutn.Business.Publicacion.PublicacionMascotaEncontrada;
import ddsutn.Repositorio.PublicacionMascotaEncontradaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionMascotaEncontradaSvc {

    @Autowired
    PublicacionMascotaEncontradaRepo publicacionMascotaEncontradaRepo;

    public List<PublicacionMascotaEncontrada> findAll() {
        return (List<PublicacionMascotaEncontrada>) publicacionMascotaEncontradaRepo.findAll();
    }

    public Optional<PublicacionMascotaEncontrada> findById(Long id) {
        return publicacionMascotaEncontradaRepo.findById(id);
    }

	public PublicacionMascotaEncontrada save(PublicacionMascotaEncontrada publicacionMascotaEncontrada) { return publicacionMascotaEncontradaRepo.save(publicacionMascotaEncontrada);
	}
}
