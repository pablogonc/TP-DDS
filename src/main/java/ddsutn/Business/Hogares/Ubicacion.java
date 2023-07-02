package ddsutn.Business.Hogares;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "ubicacion")

public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "latitud")
	private double lat;

	@Column(name = "longitud")
	@JsonProperty("long")
	private double _long;

	public Ubicacion(String direccion, double lat, double _long) {
		this.direccion = direccion;
		this.lat = lat;
		this._long = _long;
	}

	private double radianes(double num){
		return num * Math.PI /180;
	}
	public Double calcularDistancia(Ubicacion ubicacion){
		double distanciaLat = radianes(lat) - radianes(ubicacion.getLat());
		double distanciaLong = radianes(_long) - radianes(ubicacion.get_long());
		double distancia = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(distanciaLat/2),2)+Math.cos(radianes(lat))*Math.cos(radianes(ubicacion.getLat()))*Math.pow(Math.sin(distanciaLong/2),2)))* 6378137 /1000 ;
		return Math.round(distancia*100.0)/100.0;
	}

}
