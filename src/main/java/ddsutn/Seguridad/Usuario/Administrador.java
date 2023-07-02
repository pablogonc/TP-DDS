package ddsutn.Seguridad.Usuario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ddsutn.Business.Mascota.Caracteristica.Caracteristica;
import ddsutn.Business.Organizacion.Organizacion;
import ddsutn.Seguridad.Usuario.DTOs.AdministradorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@DiscriminatorValue("Administrador")

public class Administrador extends Usuario {

	@ManyToOne
	@JoinColumn(name = "id_organizacion")
    @JsonBackReference(value = "organizacion")
    private Organizacion organizacion;

    public Administrador(Administrador body) {
        this.usuario = body.getUsuario();
        this.password = body.getPassword();
        this.organizacion = body.getOrganizacion();
    }

    public void agregarCaracteristica(Caracteristica caracteristica) {
		organizacion.agregarCaracteristica(caracteristica);
	}

    public void habilitarCaracteristica(Caracteristica caracteristica){
        organizacion.agregarCaracteristica(caracteristica);
    }

    public void deshabilitarCaracteristica(Caracteristica caracteristica){
        organizacion.eliminarCaracteristica(caracteristica);
    }

    public void crearAdministrador(String usuario, String contraseña) {
        Administrador nuevoAdmin = new Administrador();
        nuevoAdmin.setUsuario(usuario);
        nuevoAdmin.setPassword(contraseña);
        nuevoAdmin.setOrganizacion(this.organizacion);

        organizacion.agregarAdministrador(nuevoAdmin);
    }

    public AdministradorDTO toDTO() {
        return new AdministradorDTO(this.id, this.usuario, this.password, this.organizacion);
    }

}
