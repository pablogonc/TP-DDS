package ddsutn.Business.Hogares;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Hogar {

	private String id;
	private String nombre;
	private Ubicacion ubicacion;
	private String telefono;
	private Admisiones admisiones;
	private Integer capacidad;
	private Integer lugares_disponibles;
	private Boolean patio;
	private List<String> caracteristicas;

}
