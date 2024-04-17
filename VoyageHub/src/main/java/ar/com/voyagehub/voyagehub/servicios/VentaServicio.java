package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.*;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class VentaServicio {
    public void crearVenta(String nombre,
                            private Long numVenta,
                            private Cliente venta,
                            private Empleado empleado,
                            private Servicio servicio,
                            private Paquete paquete,
                            private String medioPago
                            ) throws MiExcepcion {

        validarDatos(nombre, numVenta, venta, empleado, servicio, paquete, medioPago);
        Venta venta = new Venta();
        venta.setNombre(nombre);
        venta.setNumVenta(numVenta);
        venta.setVenta(venta);
        venta.setEmpleado(empleado);
        venta.setServicio(servicio);
        venta.setPaquete(paquete);
        venta.setMedioPago(medioPago);
        ventaRepositorio.save(venta);
    }
}
public void validarDatos(String nombre,
                         private Long numVenta,
                         private Cliente venta,
                         private Empleado empleado,
                         private Servicio servicio,
                         private Paquete paquete,
                         private String medioPago) throws MiExcepcion {

    validarCadena(nombre, "El nombre no puede ser vacío o nulo.");
    validarCadena(numVenta, "El apellido no puede ser vacío o nulo.");
    validarCadena(venta, "El email no puede ser vacío o nulo.");
    validarCadena(empleado, "La dirección no puede ser vacía o nula.");
    validarCadena(servicio, "La nacionalidad no puede ser vacía o nulo.");
    validarCadena(paquete, "El celular no puede ser vacío o nulo.");
    validarCadena(medioPago, "El medio de pagono puese ser vacío o nulo.");

   }

private void validarCadena(String nombre, String s) {
}
