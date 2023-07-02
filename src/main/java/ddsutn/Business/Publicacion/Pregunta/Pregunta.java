package ddsutn.Business.Publicacion.Pregunta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ddsutn.Business.Organizacion.Organizacion;
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
@Table(name = "pregunta")

public class Pregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_organizacion")
	@JsonBackReference(value = "pregunta")
	private Organizacion organizacion;

	@OneToMany(mappedBy = "pregunta", cascade = {CascadeType.ALL})
	@JsonManagedReference(value = "preguntas")
	private Set<PreguntaPublicacion> preguntasPublicacion;

	@Column(name = "pregunta")
	private String pregunta;

}

