package ddsutn.Business.SchedulerRecomendaciones;

import ddsutn.Business.Mascota.Caracteristica.Caracteristica;
import ddsutn.Business.Mascota.Mascota;
import ddsutn.Business.Publicacion.Preferencias;
import ddsutn.Business.Publicacion.PublicacionDarEnAdopcion;
import ddsutn.Business.Publicacion.PublicacionInteresado;
import ddsutn.Repositorio.PublicacionDarEnAdopcionRepo;
import ddsutn.Repositorio.PublicacionInteresadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RecomendacionesService {

	@Autowired
	private PublicacionInteresadoRepo publicacionInteresadoRepo;

	@Autowired
	private PublicacionDarEnAdopcionRepo publicacionDarEnAdopcionRepo;

	public void ejecutar() {
		try{
			this.buscarMatchEntrePublicaciones();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void buscarMatchEntrePublicaciones() {
		List<PublicacionDarEnAdopcion> publicacionesDarEnAdopcion = (List<PublicacionDarEnAdopcion>) publicacionDarEnAdopcionRepo.findAll();
		List<PublicacionInteresado> publicacionesInteresados = (List<PublicacionInteresado>) publicacionInteresadoRepo.findAll();
		publicacionesInteresados.forEach(publInteresado -> this.buscarMatchParaPublicacion(publInteresado, publicacionesDarEnAdopcion));
	}

	private void buscarMatchParaPublicacion(PublicacionInteresado publInteresado, List<PublicacionDarEnAdopcion> publicacionesDarEnAdopcion) {

		Preferencias mascotaBuscada = publInteresado.getPreferencias();
		List<PublicacionDarEnAdopcion> recomendaciones = new ArrayList<>();

		publicacionesDarEnAdopcion.forEach(publEnAdopcion -> {
			if (this.haceMatchMascota(mascotaBuscada, publEnAdopcion.getMascota())) {
				recomendaciones.add(publEnAdopcion);
			}
		});

		if (!recomendaciones.isEmpty()) {
			publInteresado.notificarRecomendacionesSemanales(recomendaciones);
		}
	}

	private Boolean haceMatchMascota(Preferencias mascotaBuscada, Mascota mascotaEnAdopcion) {
		return
				mascotaBuscada.getTipoMascota().equals(mascotaEnAdopcion.getTipo()) &&
						mascotaBuscada.getSexo().equals(mascotaEnAdopcion.getSexo()) &&
						this.coincidenLasEdades(mascotaBuscada.getEdadMax(), mascotaBuscada.getEdadMin(), mascotaEnAdopcion.getEdad()) &&
						this.tieneXEnComun(mascotaBuscada.getCaracteristicas(), mascotaEnAdopcion.getCaracteristicas(), 1)
		;
	}

	private Boolean coincidenLasEdades(int edadMax, int edadMin, int edadMascota) {
		// Si edadMin y edadMax valen 0 es porque en el form puso 'CUALQUIERA'
		return (edadMin == 0 && edadMax == 0) || (edadMascota >= edadMin && edadMascota <= edadMax);
	}


	private Boolean tieneXEnComun(Set<Caracteristica> caracteristicas1, Set<Caracteristica> caracteristicas2, int cantidadParaMatch) {
		int i = 0;
		for (Caracteristica caracteristica : caracteristicas1) {
			for(Caracteristica caracteristica2 : caracteristicas2) {
				if(caracteristica.getCaracteristica().equals(caracteristica2.getCaracteristica())) {
					i++;
				}
			}
		}
		return i >= cantidadParaMatch;
	}

}
