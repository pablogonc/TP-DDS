package ddsutn.Business.Hogares.APIHogares.dto;

public class AutenticacionRequest {

    private String email;

    public AutenticacionRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
