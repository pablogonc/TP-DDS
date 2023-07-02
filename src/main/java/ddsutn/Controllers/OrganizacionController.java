package ddsutn.Controllers;

import ddsutn.Business.Organizacion.Organizacion;
import ddsutn.Seguridad.Sesion.SesionManager;
import ddsutn.Seguridad.Usuario.Administrador;
import ddsutn.Seguridad.Usuario.Usuario;
import ddsutn.Servicios.OrganizacionSvc;
import ddsutn.Servicios.UsuariosSvc.AdministradorSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organizaciones")
@CrossOrigin
public class OrganizacionController {

    @Autowired
    private OrganizacionSvc organizacionSvc;

    @Autowired
    private AdministradorSvc administradorSvc;

    @ResponseBody
    @RequestMapping()
    public List<Organizacion> findAll(){
        return organizacionSvc.findAll();
    }

    @PostMapping(value = "/modificar")
    public ResponseEntity modificarParametros(@RequestBody Organizacion organizacion) {
        organizacionSvc.save(organizacion);
        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/normalizar-fotos")
    public ResponseEntity normalizarFotos(@RequestHeader("Authorization") String idSesion) {

        try {

            SesionManager sesionManager = SesionManager.get();
            Usuario usr = (Usuario) sesionManager.obtenerAtributo(idSesion);
            Administrador administrador = administradorSvc.findAdminByUsuario(usr.getUsuario());
            administrador.getOrganizacion().normalizarFotos();
            organizacionSvc.save(administrador.getOrganizacion());
            return ResponseEntity.status(200).build();

        } catch(Exception ex) {
            return ResponseEntity.status(400).build();
        }
    }

}
