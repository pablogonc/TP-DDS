
package ddsutn.Seguridad.Password;

import java.util.regex.Pattern;

public class ValidatePasswordNumber implements PasswordCriteria {

    private String descripcion = "Contiene numero";

    @Override
    public String getDescripcion(){
        return this.descripcion;
    }

    @Override
    public Boolean validatePassword (String password) {

        return Pattern.matches(".*[0-9].*", password);

    }
}
