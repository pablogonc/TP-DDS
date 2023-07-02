package ddsutn.Controllers;

import ddsutn.Business.Hogares.APIHogares.HogaresAPI;
import ddsutn.Business.Hogares.APIHogares.dto.PaginaHogaresResponse;
import ddsutn.Business.Hogares.Hogar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/hogares")
@CrossOrigin
public class HogaresController {

    HogaresAPI hogaresAPI = new HogaresAPI();

    @ResponseBody
    @GetMapping
    public List<Hogar> getHogares() throws Exception {
        PaginaHogaresResponse paginaHogaresResponse = hogaresAPI.getHogares(1);
        int paginas = (int) Math.floor(paginaHogaresResponse.getTotal() / 10); // Asumiendo que hay 10 hogares por pagina

        List<Hogar> hogares = new ArrayList<>();
        for (int i = 1; i <= paginas; i++) {
            hogares.addAll(hogaresAPI.getHogares(i).getHogares());
        }

        return hogares;
    }

}
