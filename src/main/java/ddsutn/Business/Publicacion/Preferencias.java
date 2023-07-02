package ddsutn.Business.Publicacion;

import ddsutn.Business.Mascota.Caracteristica.Caracteristica;
import ddsutn.Business.Mascota.Sexo;
import ddsutn.Business.Mascota.TipoMascota;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "preferencias")

public class Preferencias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoMascota tipoMascota;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	private int edadMin;
	private int edadMax;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "caracteristica_X_preferencia",
			joinColumns = { @JoinColumn(name = "id_preferencia") },
			inverseJoinColumns = { @JoinColumn(name = "id_caracteristica") }
	)
	private Set<Caracteristica> caracteristicas;

	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(
			name = "comodidad_X_preferencia",
			joinColumns = { @JoinColumn(name = "id_preferencia") },
			inverseJoinColumns = { @JoinColumn(name = "id_comodidad") }
	)
	private Set<Comodidad> comodidades;

}
