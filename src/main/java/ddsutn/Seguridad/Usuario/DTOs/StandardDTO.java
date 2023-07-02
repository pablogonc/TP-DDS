package ddsutn.Seguridad.Usuario.DTOs;

import ddsutn.Business.Persona.Duenio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardDTO {

	private Long id;
	private String password;
	private String usuario;
	private Duenio duenioAsociado;

}
