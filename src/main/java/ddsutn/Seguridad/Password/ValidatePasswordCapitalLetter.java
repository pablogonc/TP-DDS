package ddsutn.Seguridad.Password;

import java.util.regex.Pattern;

public class ValidatePasswordCapitalLetter implements PasswordCriteria {

    private String descripcion = "Contiene mayuscula";

    @Override
    public String getDescripcion(){
        return this.descripcion;
    }

    @Override
    public Boolean validatePassword(String password) {

        return Pattern.matches(".*[A-Z].*", password);

    }
}
