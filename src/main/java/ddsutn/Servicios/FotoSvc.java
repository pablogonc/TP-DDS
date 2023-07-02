package ddsutn.Servicios;

import ddsutn.Business.Mascota.Foto.Foto;
import ddsutn.Repositorio.FotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoSvc {

	@Autowired
	private FotoRepo fotoRepo;

	public Foto save(Foto foto) { return fotoRepo.save(foto); }

}
