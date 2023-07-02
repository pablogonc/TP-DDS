package ddsutn.Business.Hogares.APIHogares.dto;

import ddsutn.Business.Hogares.Hogar;
import java.util.List;

public class PaginaHogaresResponse {
    private int total;
    private int offset;
    private List<Hogar> hogares;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Hogar> getHogares() {
        return hogares;
    }

    public void setHogares(List<Hogar> hogares) {
        this.hogares = hogares;
    }

    public void mostrarHogares(){
        for (Hogar hogar : hogares) {
            System.out.println("---------------------------------");
            System.out.println("ID:'"+hogar.getId()+ "'");
            System.out.println(hogar.getNombre());
            System.out.println(hogar.getUbicacion().getDireccion());
            System.out.println("Telefono: " + hogar.getTelefono());
            System.out.println("Admisiones: ");
            System.out.println("    Perros: "+ hogar.getAdmisiones().aceptaPerros());
            System.out.println("    Gatos: "+ hogar.getAdmisiones().aceptaGatos());
            System.out.println("capacidad:" + hogar.getCapacidad());
            System.out.println("Caracteristicas: ");
            for (String caracteristica : hogar.getCaracteristicas()) {
                System.out.println("    -"+ caracteristica);
            }
            System.out.println("Patio: "+ hogar.getPatio());
        }
    }

    public Hogar seleccionarHogar(String nombre){
        return hogares.stream().filter( hogar -> hogar.getNombre().equals(nombre)).findAny().orElse(null);

    }
}
