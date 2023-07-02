package ddsutn.Servicios;

import ddsutn.Business.Publicacion.PublicacionDarEnAdopcion;
import ddsutn.Repositorio.PublicacionDarEnAdopcionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionDarEnAdopcionSvc {

    @Autowired
    PublicacionDarEnAdopcionRepo publicacionDarEnAdopcionRepo;

    public List<PublicacionDarEnAdopcion> findAll() {
        return (List<PublicacionDarEnAdopcion>) publicacionDarEnAdopcionRepo.findAll();
    }

    public Optional<PublicacionDarEnAdopcion> findById(Long id) {
        return publicacionDarEnAdopcionRepo.findById(id);
    }

	public PublicacionDarEnAdopcion save(PublicacionDarEnAdopcion pub) { return publicacionDarEnAdopcionRepo.save(pub);
	}
}
