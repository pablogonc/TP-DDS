package ddsutn.Business.Hogares;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admisiones {

    private boolean perros;
    private boolean gatos;

    public boolean aceptaPerros() {
        return perros;
    }

    public boolean aceptaGatos() {
        return gatos;
    }

}
