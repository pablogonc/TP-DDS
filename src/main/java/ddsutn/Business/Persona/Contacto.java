package ddsutn.Business.Persona;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ddsutn.Business.Notificacion.MAIL;
import ddsutn.Business.Notificacion.Notificar;
import ddsutn.Business.Notificacion.SMS;
import ddsutn.Business.Notificacion.WPP;
import lombok.*;
import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "contacto")

public class Contacto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String apellido;
	private String telefono;
	private String email;

	@ManyToOne
	@JoinColumn(name = "id_persona")
	@JsonBackReference
	private Persona persona;

	@Transient
	private List<Notificar> formasDeNotificacion;

	private String formasNotificacion;

	public void recibirNotificacion(String mensaje) {

		if(formasDeNotificacion == null){
			convertirFormasNotificacion();
		}

		formasDeNotificacion.forEach(formaNotificacion -> {
			try {
				formaNotificacion.notificar(this, mensaje);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void convertirFormasDeNotificacion() {
		formasDeNotificacion.forEach(unaFormaDeNotificacion -> formasNotificacion = formasNotificacion.concat(unaFormaDeNotificacion.getClass().getSimpleName()).concat(","));
		formasNotificacion = formasNotificacion.substring(0, formasNotificacion.length()-1); // Para sacar la ultima coma que se agregue
	}

	public void convertirFormasNotificacion() {
		formasDeNotificacion = new ArrayList<>();
		Arrays.asList(formasNotificacion.split(", ")).forEach( forma ->{
			if (forma.equals("WPP")){
				formasDeNotificacion.add(new WPP()) ;
			}
			if (forma.equals("MAIL") || forma.equals("EMAIL") ){
				formasDeNotificacion.add(new MAIL()) ;
			}
			if (forma.equals("SMS")){
				formasDeNotificacion.add(new SMS()) ;
			}
		});
	}

}
