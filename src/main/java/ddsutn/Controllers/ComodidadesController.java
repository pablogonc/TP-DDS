package ddsutn.Controllers;

import ddsutn.Business.Publicacion.Comodidad;
import ddsutn.Servicios.ComodidadSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comodidades")
@CrossOrigin
public class ComodidadesController {

	@Autowired
	private ComodidadSvc comodidadSvc;

	@ResponseBody
	@GetMapping
	public List<Comodidad> getComodidades() {
		return comodidadSvc.findAll();
	}

}