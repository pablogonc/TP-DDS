package ddsutn.Business.Publicacion;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ddsutn.Business.Mascota.Mascota;
import ddsutn.Business.Persona.Duenio;
import ddsutn.Business.Publicacion.Pregunta.PreguntaPublicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publicacion_dar_en_adopcion")
public class PublicacionDarEnAdopcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_mascota")
	private Mascota mascota;

	@OneToMany(mappedBy = "publicacion", cascade = {CascadeType.ALL})
	@JsonManagedReference(value = "pubAdopcion")
	private Set<PreguntaPublicacion> preguntas;

	public PublicacionDarEnAdopcion(Mascota mascota) {
		this.mascota = mascota;
	}
	/*
    Si esta persona encuentra a su mascota apropiada entre las publicadas, el sistema debera notificarle
    de esta situacion, por los medios preferidos de notificacion, al actual duenio de la mascota para asi
    entablar una conversacion por fuera de la plataforma.
     */

	public void notificarDuenioSobreInteresado(String emailDelInteresado) {
		String mensaje = String.format("Hay un interesado en adoptar a %s!\nComunicarse por mail a esta direccion: %s ",
				this.mascota.getNombre(),
				emailDelInteresado);

		Duenio duenioMascota = this.mascota.getDuenio();
		duenioMascota.notificarAMisContactos(mensaje);
	}

}
