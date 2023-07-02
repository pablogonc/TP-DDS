package ddsutn.Business.Mascota.Foto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ddsutn.Business.Mascota.Mascota;
import ddsutn.Business.Mascota.MascotaPerdida;
import ddsutn.Business.Organizacion.Organizacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import javax.persistence.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "foto")

public class  Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contenido_base_64", columnDefinition = "LONGTEXT")
	private String contenidoBase64;

	@ManyToOne()
	@JoinColumn(name = "id_mascota_perdida")
	@JsonBackReference(value = "fotosPerdida")
	private MascotaPerdida mascotaPerdida;

	@ManyToOne()
	@JoinColumn(name = "id_mascota")
	@JsonBackReference(value = "fotos")
	private Mascota mascota;

	@Column
	private boolean normalizada;

	public void convertirFoto(Organizacion organizacion) {
		Resolucion resolucion = organizacion.getResolucion();
		String calidad = organizacion.getCalidad();
		this.adaptarFoto(contenidoBase64, resolucion.getPixelesAlto(), resolucion.getPixelesAncho(), calidad);
	}

	private void adaptarFoto(String contenidoBase64, int pixelesAlto, int pixelesAncho, String calidad) {

		try{

			if(!normalizada){

				Path directorio = Paths.get(new File("").getAbsolutePath().concat("\\src\\main\\resources\\tmp\\"));
				Path pathInicial = Paths.get(directorio.toString() + this.id + "." + calidad);
				Path pathFinal = Paths.get(directorio.toString() + this.id + "-resized" + "." + calidad);
				String base64Inicial = contenidoBase64.split(",")[1];

				//Proceso inicial: paso de base64 a byte[] y lo escribo en un archivo
				byte[] bytes = Base64.getDecoder().decode(base64Inicial);
				Files.write(pathInicial, bytes);

				//Tomo el archivo, lo normalizo y guardo la foto en otro archivo
				Thumbnails.of(new File(String.valueOf(pathInicial))).size(pixelesAlto, pixelesAncho).outputFormat(calidad).toFile(String.valueOf(pathFinal));
				this.normalizada = true;

				// Proceso inverso para guardarla: la tomo del path, la paso a byte[], la paso a base64 y guardo la foto
				byte[] imagen = Files.readAllBytes(pathFinal);
				this.contenidoBase64 = "data:image/" + calidad + ";base64," + Base64.getEncoder().encodeToString(imagen);

				Files.delete(pathInicial);
				Files.delete(pathFinal);

			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
