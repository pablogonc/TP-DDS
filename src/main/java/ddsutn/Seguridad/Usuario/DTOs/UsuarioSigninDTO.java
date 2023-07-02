package ddsutn.Seguridad.Usuario.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSigninDTO {

    String usuario;
    String password;

}
