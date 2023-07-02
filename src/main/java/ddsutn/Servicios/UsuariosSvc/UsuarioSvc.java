package ddsutn.Servicios.UsuariosSvc;

import ddsutn.Repositorio.UsuarioRepo;
import ddsutn.Seguridad.Usuario.DTOs.UsuarioSigninDTO;
import ddsutn.Seguridad.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSvc {

    @Autowired
    UsuarioRepo usuarioRepo;

    //Spring security
    @Autowired
    PasswordEncoder encoder;

    //Guardar usuario en bd
    protected Usuario save(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    //Singin
    public Usuario signinUsuario(UsuarioSigninDTO body){
        Usuario usuario = findByUsuario(body.getUsuario());

        if(usuario != null) {
            if (!encoder.matches(body.getPassword(), usuario.getPassword())) {
                System.out.println("Contraseña incorrecta");
                throw new RuntimeException();
            }
        } else {
            System.out.println("El usuario no existe");
            throw new RuntimeException();
        }

        return usuario;
    }

    public Usuario findByUsuario(String usuario){
        return usuarioRepo.findByUsuario(usuario).orElse(null);
    }

}
