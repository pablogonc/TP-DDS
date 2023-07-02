package ddsutn.Servicios.UsuariosSvc;

import ddsutn.Repositorio.UsuarioRepo;
import ddsutn.Seguridad.Password.ValidatePassword;
import ddsutn.Seguridad.Usuario.Administrador;
import ddsutn.Seguridad.Usuario.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VoluntarioSvc {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    public Voluntario save(Voluntario voluntario) {
        return usuarioRepo.save(voluntario);
    }

    public Voluntario findVoluntarioByUsuario(String usuario) {
        return usuarioRepo.findVoluntarioByUsuario(usuario).orElse(null);
    }

    public Voluntario signupVoluntario(Voluntario body){
        ValidatePassword validator = new ValidatePassword();

        if(validator.validatePassword(body.getPassword()))
            body.setPassword(encoder.encode(body.getPassword()));
        else{
            System.out.println("Contraseña invalida");
            throw new RuntimeException();
        }

        if(findVoluntarioByUsuario(body.getUsuario()) != null){
            System.out.println("Usuario ya existente");
            throw new RuntimeException();
        }

        return save(new Voluntario(body));
    }

}
