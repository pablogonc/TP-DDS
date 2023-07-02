package ddsutn.Servicios;

import ddsutn.Business.Publicacion.PublicacionInteresado;
import ddsutn.Repositorio.PublicacionInteresadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionInteresadoSvc {

	@Autowired
	private PublicacionInteresadoRepo publicacionInteresadoRepo;

	public void save(PublicacionInteresado publicacionInteresado) {
		publicacionInteresadoRepo.save(publicacionInteresado);
	}

	public PublicacionInteresado findByCodigo(String codigo) {
		return publicacionInteresadoRepo.findByCod_Baja(codigo).orElse(null);
	}

	public void delete(PublicacionInteresado publicacionInteresado) {
		publicacionInteresadoRepo.delete(publicacionInteresado);
	}

}
