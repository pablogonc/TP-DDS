package ddsutn.Business.Organizacion;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ddsutn.Business.Hogares.Ubicacion;
import ddsutn.Business.Mascota.Caracteristica.Caracteristica;
import ddsutn.Business.Mascota.Foto.Resolucion;
import ddsutn.Business.Mascota.Mascota;
import ddsutn.Business.Publicacion.Pregunta.Pregunta;
import ddsutn.Business.Publicacion.PublicacionDarEnAdopcion;
import ddsutn.Business.Publicacion.PublicacionMascotaEncontrada;
import ddsutn.Seguridad.Usuario.Administrador;
import ddsutn.Seguridad.Usuario.Voluntario;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "organizacion")

public class Organizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ubicacion")
	private Ubicacion ubicacion;

	@Embedded
	private Resolucion resolucion;

	@Column(name = "calidad")
	private String calidad;

	@OneToMany(mappedBy = "organizacion", cascade = {CascadeType.ALL})
	@JsonManagedReference(value = "organizacion")
	private Set<Administrador> administradores;

	@OneToMany(mappedBy = "organizacion", cascade = {CascadeType.ALL})
	@JsonManagedReference(value = "voluntario")
	private Set<Voluntario> voluntarios;

	@OneToMany(mappedBy = "organizacion", cascade = {CascadeType.ALL})
	@JsonManagedReference(value = "mascota")
	private Set<Mascota> mascotasRegistradas;

	@OneToMany(mappedBy = "organizacion", cascade = {CascadeType.ALL})
	@JsonManagedReference(value = "pregunta")
	private Set<Pregunta> preguntasAdicionales;

	@OneToMany(mappedBy = "organizacion")
	@JsonManagedReference(value = "encontradas")
	private Set<PublicacionMascotaEncontrada> publicacionesMascotasEncontradas;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "caracteristica_X_organizacion",
			joinColumns = { @JoinColumn(name = "id_organizacion") },
			inverseJoinColumns = { @JoinColumn(name = "id_caracteristica") }
	)
	private Set<Caracteristica> caracPropias;


	/* *** Caracteristicas *** */

    public void agregarCaracteristica(Caracteristica unaCaracteristica) {
        this.caracPropias.add(unaCaracteristica);
    }

    public void eliminarCaracteristica(Caracteristica caracteristica) {
        this.caracPropias.remove(caracteristica);
    }

    /* *** Administradores *** */

    public void agregarAdministrador(Administrador unAdministrador) {
        this.administradores.add(unAdministrador);
    }

    public void eliminarAdministrador(Administrador unAdministrador) {
		this.administradores.remove(unAdministrador);
	}

    /* *** Preguntas *** */

    public void agregarPreguntaAdicional(Pregunta pregunta) {
        if(!this.contienePregunta(pregunta)) {
            this.preguntasAdicionales.add(pregunta);
        }
    }

    public void quitarPreguntaAdicional(Pregunta pregunta) {
        if(this.contienePregunta(pregunta)) {
            this.preguntasAdicionales.remove(pregunta);
        }
    }

    private Boolean contienePregunta(Pregunta pregunta) {
        return preguntasAdicionales.contains(pregunta);
    }

    public void normalizarFotos() {
    	for(Mascota mascota : mascotasRegistradas) {
    		mascota.getFotos().forEach(foto -> foto.convertirFoto(this));
		}
	}

}
