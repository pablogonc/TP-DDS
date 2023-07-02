package ddsutn.Business.Organizacion;

import ddsutn.Business.Hogares.Ubicacion;
import ddsutn.Business.Mascota.Caracteristica.Caracteristica;
import ddsutn.Business.Mascota.Foto.Resolucion;
import ddsutn.Business.Publicacion.Pregunta.Pregunta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizacionDTO {

	Long id;
	String nombre;
	Ubicacion ubicacion;
	Resolucion resolucion;
	String calidad;
	Set<Pregunta> preguntasAdicionales;
	Set<Caracteristica> caracPropias;

}
