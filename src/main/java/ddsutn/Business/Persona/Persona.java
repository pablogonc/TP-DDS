package ddsutn.Business.Persona;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ddsutn.Business.Notificacion.MAIL;
import ddsutn.Business.Notificacion.Notificar;
import ddsutn.Business.Notificacion.SMS;
import ddsutn.Business.Notificacion.WPP;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol", discriminatorType = DiscriminatorType.STRING)

public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	protected TipoDcto tipoDocumento;

	protected String nroDocumento;
	protected Date fechaDeNacimiento;

	@OneToMany(mappedBy = "persona", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JsonManagedReference
	protected List<Contacto> otrosContactos;

	protected String domicilio;
	protected String nombre;
	protected String apellido;
	protected String telefono;
	protected String email;

	@Transient
	private List<Notificar> formasDeNotificacion;

	private String formasNotificacion;

	public Persona(TipoDcto tipoDocumento, String nroDocumento, Date fechaDeNacimiento, List<Contacto> otrosContactos, String domicilio, String nombre, String apellido, String telefono, String email, List<Notificar> formasDeNotificacion) {
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.otrosContactos = otrosContactos;
		this.domicilio = domicilio;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.formasDeNotificacion = formasDeNotificacion;
	}

	public void notificarAMisContactos(String mensaje) {
		this.recibirNotificacion(mensaje);
		this.otrosContactos.forEach(unContacto -> unContacto.recibirNotificacion(mensaje));
	}

	public void recibirNotificacion(String mensaje) {

		if(this.formasDeNotificacion == null){
			this.convertirFormasNotificacion();
		}

		this.formasDeNotificacion.forEach(formaNotificacion -> {
			try {
				Contacto miContacto = new Contacto(null, null, null, this.telefono, this.email, null, null, null);
				formaNotificacion.notificar(miContacto, mensaje);
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
		Arrays.asList(formasNotificacion.split(", ")).forEach(forma ->{
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
