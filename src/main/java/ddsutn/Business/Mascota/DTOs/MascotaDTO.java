package ddsutn.Business.Mascota.DTOs;

import ddsutn.Business.Mascota.Caracteristica.Caracteristica;
import ddsutn.Business.Mascota.Foto.Foto;
import ddsutn.Business.Mascota.Sexo;
import ddsutn.Business.Mascota.TipoMascota;
import ddsutn.Business.Organizacion.OrganizacionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotaDTO {

	Long id;
	String id_QR;
	TipoMascota tipo;
	Sexo sexo;
	String nombre;
	String apodo;
	int edad;
	String descripcion;
	Set<Foto> fotos;
	Set<Caracteristica> caracteristicas;
	OrganizacionDTO organizacion;

}
