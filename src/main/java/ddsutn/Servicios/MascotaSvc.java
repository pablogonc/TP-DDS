package ddsutn.Servicios;

import ddsutn.Business.Mascota.Mascota;
import ddsutn.Repositorio.MascotaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MascotaSvc {

    @Autowired
    private MascotaRepo mascotaRepo;

    public void save(Mascota mascota) {
        mascotaRepo.save(mascota);
    }

    public void saveAll(List<Mascota> mascota) {
        mascotaRepo.saveAll(mascota);
    }

	public Mascota findById(Long id) {
    	return mascotaRepo.findById(id).orElse(null);
	}

    public Mascota findById_QR(String id_qr) {
        return mascotaRepo.findById_QR(id_qr).orElse(null);
    }

}
