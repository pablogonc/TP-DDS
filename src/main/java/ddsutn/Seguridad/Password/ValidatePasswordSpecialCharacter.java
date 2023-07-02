
package ddsutn.Seguridad.Password;

import java.util.regex.Pattern;

public class ValidatePasswordSpecialCharacter implements PasswordCriteria {

    private String descripcion = "Contiene caracter especial";

    @Override
    public String getDescripcion(){
        return this.descripcion;
    }

    @Override
    public Boolean validatePassword (String password) {
        return Pattern.matches(".*[@#$/%!_*?¿-].*", password);

    }
}
