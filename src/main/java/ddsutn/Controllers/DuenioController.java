package ddsutn.Controllers;

import ddsutn.Business.Mascota.Mascota;
import ddsutn.Business.Persona.Duenio;
import ddsutn.Servicios.DuenioSvc;
import ddsutn.Servicios.MascotaSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/dueño")
@CrossOrigin
public class DuenioController {

    @Autowired
    private DuenioSvc duenioSvc;

    @Autowired
    private MascotaSvc mascotaSvc;

    @PostMapping("/registrar")
    public ResponseEntity<Object> crearDuenio(@RequestBody Duenio duenio) {
        duenio.getMascotas().forEach(mascota -> {
            mascota.setId_QR(UUID.randomUUID().toString());
        });
        /* QR? */
        try {
            duenioSvc.save(duenio);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Duenio> obtenerDuenioPorIdMascota(@PathVariable Long id) {
        try {
            Mascota mascota = mascotaSvc.findById(id);
            return ResponseEntity.status(200).body(mascota.getDuenio());
        } catch(Exception ex) {
            return ResponseEntity.status(200).build();
        }
    }

}
