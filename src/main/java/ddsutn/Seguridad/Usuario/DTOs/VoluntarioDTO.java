package ddsutn.Seguridad.Usuario.DTOs;

import ddsutn.Business.Organizacion.Organizacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoluntarioDTO {

	private Long id;
	private String usuario;
	private String password;
	private Organizacion organizacion;

}
