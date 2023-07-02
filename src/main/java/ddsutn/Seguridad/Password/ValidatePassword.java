package ddsutn.Seguridad.Password;

import java.io.IOException;
import java.util.Arrays;

public class ValidatePassword {

    PasswordCriteria[] criterios = {
            new ValidatePasswordLength(),
            new ValidatePasswordNumber(),
            new ValidatePasswordCapitalLetter(),
            new ValidatePasswordSpecialCharacter(),
            new ValidatePasswordDictionary()
    };

    public Boolean validatePassword(String password) {
        return Arrays.asList(criterios).stream()
                .map(unCriterio -> {
                    try {
                        return this.validarSegun(unCriterio, password);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    return null;
                })
                .reduce(Boolean::logicalAnd).get();
    }

    private Boolean validarSegun(PasswordCriteria unCriterio, String unaPassword) throws IOException {
        return unCriterio.validatePassword(unaPassword);
    }

}




