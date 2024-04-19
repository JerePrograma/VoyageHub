package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Paquete;
import ar.com.voyagehub.voyagehub.entidades.Servicio;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Data

public class PaqueteServicio {
    private Long codigoServicio;
    private String nombreServicio;
    private Float costoServicio;

    // Constructor, getters y setters

//    public boolean crearPaquete(List<Servicio> servicios) {
//        Paquete paquete = new Paquete();
//        paquete.setServiciosIncluidos(Collections.singletonList((Servicio) servicios));
//        return paquete;
//        public boolean validarDatos() {
//            return this.nombreServicio != null && this.costoServicio != null;
//    }

}
