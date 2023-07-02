package ddsutn.Business.Publicacion.Pregunta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ddsutn.Business.Publicacion.PublicacionDarEnAdopcion;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "preguntas_x_publicacion")

public class PreguntaPublicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "id_publicacion")
	@JsonBackReference(value = "pubAdopcion")
	private PublicacionDarEnAdopcion publicacion;

	@ManyToOne()
	@JoinColumn(name = "id_pregunta")
	@JsonBackReference(value = "preguntas")
	private Pregunta pregunta;

	@Column(name = "respuesta")
	private String respuesta;

}
