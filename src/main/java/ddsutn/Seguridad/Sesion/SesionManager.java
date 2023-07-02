package ddsutn.Seguridad.Sesion;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SesionManager {

    private static SesionManager instancia;

    private Map<String, Object> sesiones;

    private SesionManager() {
        this.sesiones = new HashMap<>();
    }

    public static SesionManager get() {
        if (instancia == null) {
            instancia = new SesionManager();
        }
        return instancia;
    }

    public String crear(Object usuarioSesion) {
        String id = UUID.randomUUID().toString();
        this.sesiones.put(id, usuarioSesion);
        return id;
    }

    public Object obtenerAtributo(String id) {
        return this.sesiones.get(id);
    }

    public Object eliminar(String id) {
        //esto no elimina la cookie del frontend
        return this.sesiones.remove(id);
    }

}