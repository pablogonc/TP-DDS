package ddsutn.Controllers;

import ddsutn.Business.Mascota.Mascota;
import ddsutn.Business.Mascota.MascotaPerdida;
import ddsutn.Business.Publicacion.PublicacionMascotaEncontrada;
import ddsutn.Servicios.MascotaPerdidaSvc;
import ddsutn.Servicios.MascotaSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/mascota-perdida")
public class MascotaPerdidaController {

    @Autowired
    private MascotaPerdidaSvc mascotaPerdidaSvc;

    @Autowired
    private MascotaSvc mascotaSvc;

    @PostMapping("/crear")
    public ResponseEntity<Object> crearMascotaEncontradaConQR(@RequestBody PublicacionMascotaEncontrada body, @RequestHeader("Authorization") String id_qr) {

        MascotaPerdida mascotaPerdida = body.getMascota();
        Mascota mascotaQR = mascotaSvc.findById_QR(id_qr); // exception?
        mascotaPerdida.setMascotaAsociada(mascotaQR);

        try {
            mascotaPerdidaSvc.save(mascotaPerdida);
            mascotaQR.meEncontraron(body.getMascota().getRescatista());
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

    }

}
