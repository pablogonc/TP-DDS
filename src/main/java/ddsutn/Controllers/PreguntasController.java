package ddsutn.Controllers;

import ddsutn.Business.Publicacion.Pregunta.Pregunta;
import ddsutn.Servicios.PreguntaSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/preguntas")
public class PreguntasController {

	@Autowired
	private PreguntaSvc preguntaSvc;

	@ResponseBody
	@GetMapping("/obtener")
	public ResponseEntity<List<Pregunta>> findAll(){
		try {
			return ResponseEntity.status(200).body(preguntaSvc.findAllGlobales());
		} catch(Exception ex) {
			return ResponseEntity.status(400).build();
		}
	}

}
