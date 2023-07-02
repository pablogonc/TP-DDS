package ddsutn.Controllers;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import ddsutn.Business.Mascota.Foto.QR;
import ddsutn.Business.Persona.Duenio;
import ddsutn.Seguridad.Sesion.LoginResponse;
import ddsutn.Seguridad.Sesion.SesionManager;
import ddsutn.Seguridad.Usuario.Administrador;
import ddsutn.Seguridad.Usuario.DTOs.*;
import ddsutn.Seguridad.Usuario.StandardUser;
import ddsutn.Seguridad.Usuario.Usuario;
import ddsutn.Seguridad.Usuario.Voluntario;
import ddsutn.Servicios.UsuariosSvc.AdministradorSvc;
import ddsutn.Servicios.UsuariosSvc.StandardSvc;
import ddsutn.Servicios.UsuariosSvc.UsuarioSvc;
import ddsutn.Servicios.UsuariosSvc.VoluntarioSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.DiscriminatorValue;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioSvc usuarioSvc;

    @Autowired
    private AdministradorSvc administradorSvc;

    @Autowired
    private StandardSvc standardSvc;

    @Autowired
    private VoluntarioSvc voluntarioSvc;

    private QR QR = new QR();

    //Creacion de usuarios
    @PostMapping(value = "/registrar-admin")
    public ResponseEntity<AdministradorDTO> signupAdmin(@RequestBody Administrador body) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(administradorSvc.signupAdmin(body).toDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/registrar-estandar")
    public ResponseEntity<StandardDTO> signupStandardUser(@RequestBody StandardUser body) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(standardSvc.signupStandardUser(body).toDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/registrar-voluntario")
    public ResponseEntity<VoluntarioDTO> signupVoluntario(@RequestBody Voluntario body) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(voluntarioSvc.signupVoluntario(body).toDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //Sign in

    @PostMapping(value = "/iniciar-sesion")
    public ResponseEntity<LoginResponse> login(@RequestBody UsuarioSigninDTO usuarioSigninDTO) {

        Usuario usr = usuarioSvc.signinUsuario(usuarioSigninDTO);                       // se valida contraseña y nombre de usuario. no importa el rol

        SesionManager sesionManager = SesionManager.get();
        String rol = usr.getClass().getAnnotation(DiscriminatorValue.class).value();    // pregunto cual es el rol para mandarselo a Vue para que sepa si esta iniciando sesion un Admin, Voluntario o Standard (y saber que pantalla principal mostrarle)

        String idSesion = sesionManager.crear(usr);                                     // La idea en el Sesion Manager (por ahora) es vincular al idSesion con un usuario y contraseña (sin importar su rol)

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(idSesion, rol));

        //return new LoginResponse(idSesion, rol);                                         Como respuesta devuelvo el idSesion para guardarlo en localStorage y el rol lo paso para que Vue sepa a que pantalla principal redirigir al usuario
    }

    @GetMapping(value = "/datos-administrador") // usando sesion
    public ResponseEntity<AdministradorDTO> obtenerMisDatos(@RequestHeader("Authorization") String idSesion) {

        SesionManager sesionManager = SesionManager.get();
        Usuario usr = (Usuario) sesionManager.obtenerAtributo(idSesion);                // Obtengo el usuario asociado a la sesion

        Administrador administrador = administradorSvc.findAdminByUsuario(usr.getUsuario()); // Con el nombre de usuario obtengo todos los datos de ese usuario

        AdministradorDTO administradorDTO = administrador.toDTO();                      // Paso esos datos a un DTO

        return ResponseEntity.status(200).body(administradorDTO);                       // Retorno el DTO
    }

    @GetMapping(value = "/datos-estandar") // usando sesion
    public ResponseEntity<StandardDTO> obtenerMisDatosEstandar(@RequestHeader("Authorization") String idSesion) {

        SesionManager sesionManager = SesionManager.get();
        Usuario usr = (Usuario) sesionManager.obtenerAtributo(idSesion);                // Obtengo el usuario asociado a la sesion

        StandardUser estandar = standardSvc.findStandardByUsuario(usr.getUsuario()); // Con el nombre de usuario obtengo todos los datos de ese usuario

        StandardDTO standardDTO = estandar.toDTO();                                  // Paso esos datos a un DTO

        return ResponseEntity.status(200).body(standardDTO);                       // Retorno el DTO
    }

    // Es igual que el de arriba pero en vez de retornar el id_qr retorna el QR en base 64
    @GetMapping(value="/datos-estandar-qr")
    public ResponseEntity<StandardDTO> obtenerQRMascota(@RequestHeader("Authorization") String idSesion) throws NotFoundException, IOException, WriterException {
        SesionManager sesionManager = SesionManager.get();
        Usuario usr = (Usuario) sesionManager.obtenerAtributo(idSesion);
        StandardUser estandar = standardSvc.findStandardByUsuario(usr.getUsuario());

        StandardDTO standardDTO = estandar.toDTO();
        standardDTO.getDuenioAsociado().getMascotas().forEach(mascota -> {
            try {
                mascota.setId_QR(QR.generarQR(mascota.getId_QR()));
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.status(200).body(standardDTO);

    }

    @PostMapping(value = "/datos-estandar/actualizar")
    public ResponseEntity<StandardDTO> actualizarMisDatosEstandar(@RequestBody Duenio duenio, @RequestHeader("Authorization") String idSesion) {
        SesionManager sesionManager = SesionManager.get();
        Usuario usr = (Usuario) sesionManager.obtenerAtributo(idSesion);
        StandardUser estandar = standardSvc.findStandardByUsuario(usr.getUsuario());
        estandar.setDuenioAsociado(duenio);
        return ResponseEntity.status(HttpStatus.CREATED).body(standardSvc.save(estandar).toDTO());
    }

    @GetMapping(value = "/datos-voluntario")
    public ResponseEntity<VoluntarioDTO> obtenerMisDatosVoluntario(@RequestHeader("Authorization") String idSesion) {

        SesionManager sesionManager = SesionManager.get();
        Usuario usr = (Usuario) sesionManager.obtenerAtributo(idSesion);
        Voluntario voluntario = voluntarioSvc.findVoluntarioByUsuario(usr.getUsuario());
        VoluntarioDTO voluntarioDTO = voluntario.toDTO();
        return ResponseEntity.status(200).body(voluntarioDTO);

    }


}


