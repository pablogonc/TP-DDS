package ddsutn.Business.Mascota;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ddsutn.Business.Hogares.Ubicacion;
import ddsutn.Business.Mascota.Foto.Foto;
import ddsutn.Business.Persona.Rescatista;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "mascota_perdida")

public class MascotaPerdida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "mascotaPerdida", cascade = {CascadeType.ALL})
	@JsonManagedReference(value = "fotosPerdida")
	private Set<Foto> fotos;

	@Column(name = "estado")
	private String estado;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_ubicacion")
	private Ubicacion ubicacion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_rescatista")
	@JsonManagedReference(value = "rescatistaPerdida")
	private Rescatista rescatista;

	@Enumerated(EnumType.STRING)
	private TipoMascota tipo;

	// Si es con QR
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_mascota")
	@JsonManagedReference(value = "mascAsociada")
	private Mascota mascotaAsociada;

	public MascotaPerdida(Set<Foto> foto, String estado, Ubicacion ubicacion, TipoMascota tipo) {
		this.fotos = foto;
		this.estado = estado;
		this.ubicacion = ubicacion;
		this.tipo = tipo;
	}

}