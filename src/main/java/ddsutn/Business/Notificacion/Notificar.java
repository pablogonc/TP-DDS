package ddsutn.Business.Notificacion;

import ddsutn.Business.Persona.Contacto;
import java.io.IOException;

public interface Notificar {

    void notificar(Contacto contacto, String mensaje) throws IOException;

}
