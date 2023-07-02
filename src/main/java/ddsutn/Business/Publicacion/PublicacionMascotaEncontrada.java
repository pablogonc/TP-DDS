package ddsutn.Business.Publicacion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ddsutn.Business.Mascota.MascotaPerdida;
import ddsutn.Business.Organizacion.Organizacion;
import ddsutn.Business.Persona.Contacto;
import ddsutn.Business.Persona.Duenio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "publicacion_mascota_encontrada")
public class PublicacionMascotaEncontrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_organizacion")
	@JsonBackReference(value = "encontradas")
	private Organizacion organizacion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_mascota")
	private MascotaPerdida mascota;

	@Column(name = "aceptada")
	private Boolean aceptada;

    public void aceptarPublicacionMascotaEncontrada() {
        this.aceptada = true;
    }

	public void notificarDuenioAparecido(Duenio duenioAparecido) {
		String mensaje = String.format("Ha aparecido el duenio de la mascota que ha rescatado!\nComunicarse con %s %s: Tel. %s - Email %s",
				duenioAparecido.getNombre(),
				duenioAparecido.getApellido(),
				duenioAparecido.getTelefono(),
				duenioAparecido.getEmail());
		this.getMascota().getRescatista().notificarAMisContactos(mensaje);
	}

}
