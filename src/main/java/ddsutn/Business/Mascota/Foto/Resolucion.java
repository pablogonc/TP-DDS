package ddsutn.Business.Mascota.Foto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Resolucion {

	private int pixelesAlto;
	private int pixelesAncho;

}
