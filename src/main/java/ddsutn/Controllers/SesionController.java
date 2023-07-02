package ddsutn.Controllers;

import ddsutn.Seguridad.Sesion.SesionManager;
import ddsutn.Seguridad.Usuario.Usuario;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sesion")
@CrossOrigin
public class SesionController {

    @GetMapping(value = "/validar") // usando sesion
    @ResponseBody
    public Boolean validarSesion(@RequestHeader("Authorization") String idSesion) {

        SesionManager sesionManager = SesionManager.get();
        Usuario usr = (Usuario) sesionManager.obtenerAtributo(idSesion);

        return (usr != null);

    }

    @GetMapping(value = "/eliminar")
    public void eliminarSesion(@RequestHeader("Authorization") String idSesion) {

        SesionManager sesionManager = SesionManager.get();
        sesionManager.eliminar(idSesion);

    }

}
