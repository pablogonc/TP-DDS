package ddsutn.Controllers;

import ddsutn.Business.Publicacion.PublicacionDarEnAdopcion;
import ddsutn.Seguridad.Sesion.SesionManager;
import ddsutn.Seguridad.Usuario.StandardUser;
import ddsutn.Servicios.PublicacionDarEnAdopcionSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/adoptar")
@CrossOrigin
public class AdoptarController {

    @Autowired
    private PublicacionDarEnAdopcionSvc publicacionDarEnAdopcionSvc;

    @ResponseBody
    @GetMapping("/publicaciones")
    public List<PublicacionDarEnAdopcion> findAll(){
        return publicacionDarEnAdopcionSvc.findAll();
    }

    @ResponseBody
    @GetMapping("/publicaciones/{id}")
    public Optional<PublicacionDarEnAdopcion> findById(@PathVariable Long id) {
        return publicacionDarEnAdopcionSvc.findById(id);
    }

    @GetMapping(value = "/publicaciones/{id}/adoptar") // usando sesion
    @ResponseBody
    public Boolean adoptarMascota(@RequestHeader("Authorization") String idSesion,@PathVariable Long id) {
        System.out.println("Se quiere adoptar una mascota");
        SesionManager sesionManager = SesionManager.get();
        StandardUser usr = (StandardUser) sesionManager.obtenerAtributo(idSesion);
        Optional<PublicacionDarEnAdopcion> publicacion = publicacionDarEnAdopcionSvc.findById(id);

        if( publicacion.isPresent() && usr != null){
            publicacion.get().notificarDuenioSobreInteresado(usr.getDuenioAsociado().getEmail());
        }else{
            return false;
        }

        return true;
    }
}
