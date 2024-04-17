package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.entidades.Servicio;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class ServicioServicio {
    public void crearServicio( private Long codigoServicio;
    private String nombre;
    private String descripcionBreve;
    private String destinoServicio;
    @Column(columnDefinition = "DATE")
    private LocalDate fechaServicio;
    private Float costoServicio;
    )throws MiExcepcion {

        validarDatos( codigoServicio, nombre, descripcionBreve, destinoServicio, fechaServicio, costoServicio);
        Servicio servicio = new Servicio();
        servicio.setCodigoServicio();
        servicio.setNombre(nombre);
        servicio.setDescripcionBreve(descripcionBreve);
        servicio.setDestinoServicio(destinoServicio);
        servicio.setFechaServicio(fechaServicio);
        servicio.setCostoServicio(costoServicio);
        servicioRepositorio.save(servicio);
    }
}
        private String nombre;
        private String descripcionBreve;
        private String destinoServicio;
        @Column(columnDefinition = "DATE")
        private LocalDate fechaServicio;
        private Float costoServicio;
) throws MiExcepcion {

        validarCadena(nombre, "El nombre no puede ser vacío o nulo.");
        validarCadena(descripcionBreve, "La descripion no puede ser vacío o nulo.");
        validarCadena(destinoServicio, "El destino del Servicio no puede ser vacío o nulo.");
        validarCadena(fechaServicio, "La fecha de Servicio no puede ser vacía o nula.");
        validarCadena(costoServicio, "El costo de Servicio no puede ser vacía o nula.");
  }
//un error me sugeria crear el metodo
private void validarCadena(String nombre, String s) {
}

