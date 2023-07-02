package ddsutn.Seguridad.Password;

public class ValidatePasswordLength implements PasswordCriteria {

    private int minLength = 8;
    private int maxLength = 64;
    private String descripcion = "Cumple longitud";

    @Override
    public String getDescripcion(){
        return this.descripcion;
    }

    @Override
    public Boolean validatePassword(String password) {
        return (password.length() >= this.minLength) && (password.length() <= this.maxLength);
    }
}

