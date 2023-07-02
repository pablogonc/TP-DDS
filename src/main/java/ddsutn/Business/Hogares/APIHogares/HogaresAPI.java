package ddsutn.Business.Hogares.APIHogares;

import ddsutn.Business.Hogares.APIHogares.dto.AutenticacionRequest;
import ddsutn.Business.Hogares.APIHogares.dto.AutenticacionResponse;
import ddsutn.Business.Hogares.APIHogares.dto.PaginaHogaresResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.client.WebClient;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class HogaresAPI {


    public String obtenerToken(String mail) throws Exception {
        WebClient client = WebClient.create("https://api.refugiosdds.com.ar/api/usuarios");

        AutenticacionRequest autenticacionRequest = new AutenticacionRequest(mail);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(autenticacionRequest);
        System.out.println("Autent request = " + requestBody);


        Response response = client
                .header("Content-Type", "application/json")
                .post(requestBody);

        int status = response.getStatus();
        String responseBody = response.readEntity(String.class);
        if (status == 200) {

            AutenticacionResponse autenticacionResponse = objectMapper.readValue(responseBody, AutenticacionResponse.class);
            return autenticacionResponse.getBearer_token();
        } else {
            System.out.println("Error response = " + responseBody);
            throw new Exception("Error en la llamada a /api/usuarios");
        }
    }

    public PaginaHogaresResponse getHogares(int offset) throws Exception {

        WebClient client = WebClient.create("https://api.refugiosdds.com.ar/api/hogares");

        ObjectMapper objectMapper = new ObjectMapper();

        MultivaluedMap<String , String > headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization",System.getenv("APIKEYHOGARES"));

        Response response = client
                .headers(headers)
                .query("offset",offset)
                .get();

        int status = response.getStatus();
        String responseBody = response.readEntity(String.class);
        switch (status){
            case 200:
                return  objectMapper.readValue(responseBody, PaginaHogaresResponse.class);
            case 400:
                throw new Exception("Error: offset no valido");
            case 401:
                throw new Exception("Error: acceso no autorizado");
            default:
                throw new Exception("Error en la llamada a /api/hogares");
        }

    }

}
