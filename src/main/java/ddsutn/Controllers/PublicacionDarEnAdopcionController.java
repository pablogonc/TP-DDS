package ddsutn.Controllers;

import ddsutn.Business.Publicacion.PublicacionDarEnAdopcion;
import ddsutn.Servicios.PublicacionDarEnAdopcionSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dar-en-adopcion")
@CrossOrigin
public class PublicacionDarEnAdopcionController {

	@Autowired
	private PublicacionDarEnAdopcionSvc publicacionDarEnAdopcionSvc;

	@PostMapping(value = "/guardar")
	public ResponseEntity guardarPublicacion(@RequestBody PublicacionDarEnAdopcion pub) {

		try {
			publicacionDarEnAdopcionSvc.save(pub);
			return ResponseEntity.status(200).build();
		} catch (Exception ex) {
			return ResponseEntity.status(400).build();
		}
	}
}
