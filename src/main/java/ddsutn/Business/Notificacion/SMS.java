package ddsutn.Business.Notificacion;

import ddsutn.Business.Persona.Contacto;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.instance.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.io.IOException;
import java.util.Properties;

/* No hay mas credito con esta cuenta, por eso cae en Exception */

public class SMS implements Notificar {

    @Override
    public void notificar(Contacto contacto, String mensaje) throws IOException {

        Properties prop = new Properties();
        InputStream input = new FileInputStream("src/main/java/ddsutn/Business/Notificacion/Config/SMS.prop");
        prop.load(input);

        RestClient client = new RestClient(prop.getProperty("username"), prop.getProperty("token"));
        TMNewMessage m = client.getResource(TMNewMessage.class);
        m.setText(mensaje);
        m.setPhones(Arrays.asList(contacto.getTelefono()));
        try {
            m.send();
        } catch (final RestException e) {
            System.out.println(e.getErrors());
            throw new RuntimeException(e);
        }
        System.out.println(m.getId());
    }
}
