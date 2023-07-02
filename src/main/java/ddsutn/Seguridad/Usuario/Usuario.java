package ddsutn.Seguridad.Usuario;

import ddsutn.Seguridad.Usuario.DTOs.UsuarioRDTO;
import lombok.*;
import org.hibernate.annotations.DiscriminatorOptions;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorOptions(force = true)

public abstract class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected String usuario;
	protected String password;

	public UsuarioRDTO toRDTO(){
		return new UsuarioRDTO(this.id, this.usuario);
	}

}
