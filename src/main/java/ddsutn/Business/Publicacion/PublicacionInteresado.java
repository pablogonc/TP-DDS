package ddsutn.Business.Publicacion;

import ddsutn.Business.Persona.Contacto;
import ddsutn.Business.Notificacion.MAIL;
import lombok.*;
import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "publicacion_interesado")

public class PublicacionInteresado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email")
	private String emailDelInteresado;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_preferencias")
	private Preferencias preferencias;

	@Column
	private String cod_Baja;

	public void notificarAlInteresado(String mensaje) {
		MAIL notificacionMail = new MAIL();

		Contacto contactoInteresado = new Contacto();
		contactoInteresado.setEmail(this.emailDelInteresado);

		try {
			notificacionMail.notificar(contactoInteresado, mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void otorgarLinkDeBajaAlInteresado() {
		this.cod_Baja = UUID.randomUUID().toString();
		this.notificarAlInteresado("Felicitaciones! Estás un paso mas cerca de adoptar una mascota! La publicación se ha creado correctamente. \nTe dejamos este link por si en algún momento deseas darla de baja: " + "https://patitashtml.herokuapp.com/Bajar_Publicacion.html?cod=" + cod_Baja);
		// El link no funciona. Con el deploy hay que actualizarlo
	}

	/* *** Recomendaciones *** */

	public void notificarRecomendacionesSemanales(List<PublicacionDarEnAdopcion> recomendaciones) {
		String mensajeRecomendaciones = "Hiciste match con las siguientes mascotas: \n";
		for(PublicacionDarEnAdopcion pub : recomendaciones) {
			String nombreMascota = pub.getMascota().getNombre();
			mensajeRecomendaciones = mensajeRecomendaciones.concat("¡" + nombreMascota + "!\n");
		}
		mensajeRecomendaciones = mensajeRecomendaciones.concat("Puedes ingresar en la web: https://patitashtml.herokuapp.com/Publicaciones_Adopcion.html \n¡Aquí encontrarás su publicación para continuar con la adopción! \n");
		this.notificarAlInteresado(mensajeRecomendaciones);
	}

}
