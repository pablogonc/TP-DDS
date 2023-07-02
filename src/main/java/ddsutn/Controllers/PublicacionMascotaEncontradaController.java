package ddsutn.Controllers;

import ddsutn.Business.Organizacion.Organizacion;
import ddsutn.Business.Publicacion.PublicacionMascotaEncontrada;
import ddsutn.Seguridad.Sesion.SesionManager;
import ddsutn.Seguridad.Usuario.StandardUser;
import ddsutn.Servicios.PublicacionMascotaEncontradaSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/perdidas")
public class PublicacionMascotaEncontradaController {

    @Autowired
    private PublicacionMascotaEncontradaSvc publicacionMascotaEncontradaSvc;

    @Autowired
    private OrganizacionController organizacionController;

    @ResponseBody
    @GetMapping("/publicaciones")
    public List<PublicacionMascotaEncontrada> findAll(){
        return publicacionMascotaEncontradaSvc.findAll();
    }

    @ResponseBody
    @GetMapping("/publicaciones/{id}")
    public Optional<PublicacionMascotaEncontrada> findById(@PathVariable Long id) {
        return publicacionMascotaEncontradaSvc.findById(id);
    }

    @ResponseBody
    @PostMapping("/publicaciones")
    public ResponseEntity guardarPublicacion(@RequestBody PublicacionMascotaEncontrada publicacionMascotaEncontrada) {
        try{
            PublicacionMascotaEncontrada pub = publicacionMascotaEncontradaSvc.findById(publicacionMascotaEncontrada.getId()).orElse(null);
            publicacionMascotaEncontrada.setOrganizacion(pub.getOrganizacion());
            publicacionMascotaEncontradaSvc.save(publicacionMascotaEncontrada);
            return ResponseEntity.status(200).build();
        }catch(Exception ex){
            return ResponseEntity.status(400).build();
        }

    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearPublicacionMascotaEncontrada(@RequestBody PublicacionMascotaEncontrada body) {

        /* Se le asigna a la organizacion mas cercana. Funciona mal */

        List<Organizacion> organizaciones = organizacionController.findAll();

        Double minDist = Double.MAX_VALUE;
        Organizacion orgMin = null;
        for (Organizacion org : organizaciones) {
            if (body.getMascota().getUbicacion().calcularDistancia(org.getUbicacion()) < minDist) {
                orgMin = org;
                minDist = body.getMascota().getUbicacion().calcularDistancia(orgMin.getUbicacion());
            }
        }

        body.setOrganizacion(orgMin);

        try {
            publicacionMascotaEncontradaSvc.save(body);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/publicaciones/{id}/contactar")
    @ResponseBody
    public Boolean contactar(@RequestHeader("Authorization") String idSesion, @PathVariable Long id) {

        SesionManager sesionManager = SesionManager.get();
        StandardUser usr = (StandardUser) sesionManager.obtenerAtributo(idSesion);
        Optional<PublicacionMascotaEncontrada> publicacion = publicacionMascotaEncontradaSvc.findById(id);

        if( publicacion.isPresent() && usr != null){
            publicacion.get().notificarDuenioAparecido(usr.getDuenioAsociado());
        }else{
            return false;
        }

        return true;
    }


}
