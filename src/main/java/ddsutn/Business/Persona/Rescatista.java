package ddsutn.Business.Persona;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ddsutn.Business.Mascota.MascotaPerdida;
import ddsutn.Business.Notificacion.Notificar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@DiscriminatorValue("Rescatista")

public class Rescatista extends Persona {

	@OneToOne(mappedBy = "rescatista")
	@JsonBackReference(value = "rescatistaPerdida")
	private MascotaPerdida mascotaPerdida;

	public Rescatista(TipoDcto tipoDocumento, String nroDocumento, Date fechaDeNacimiento, List<Contacto> otrosContactos, String domicilio, String nombre, String apellido, String telefono, String email, List<Notificar> formasDeNotificacion, MascotaPerdida mascotaPerdida) {
		super(tipoDocumento, nroDocumento, fechaDeNacimiento, otrosContactos, domicilio,nombre, apellido, telefono, email, formasDeNotificacion);
		this.mascotaPerdida = mascotaPerdida;
	}

}
