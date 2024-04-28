package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.*;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.repositorios.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VentaServicio {
    final
    VentaRepositorio ventaRepositorio;

    public VentaServicio(VentaRepositorio ventaRepositorio) {
        this.ventaRepositorio = ventaRepositorio;
    }

    public void crearVenta(String nombre,
                           Long numVenta,
                           Cliente cliente,
                           Empleado empleado,
                           Servicio servicio,
                           Paquete paquete,
                           String medioPago
    ) throws MiExcepcion {
        validarDatos(nombre, empleado, servicio, paquete, medioPago);
        Venta venta = new Venta();
        venta.setNumVenta(numVenta);
        venta.setEmpleado(empleado);
        venta.setServicio(servicio);
        venta.setPaquete(paquete);
        venta.setMedioPago(medioPago);
        ventaRepositorio.save(venta);
    }


    public void validarDatos(String nombre,
                             Empleado empleado,
                             Servicio servicio,
                             Paquete paquete,
                             String medioPago) throws MiExcepcion {
        validarCadena(medioPago, "El medio de pago no puede estar vacío ni ser nulo");
        validarCadena(nombre, "El nombre no puede estar vacío ni ser nulo");
    }

    void validarCadena(String valor, String mensajeError) throws MiExcepcion {
        if (valor == null || valor.trim().isEmpty()) {
            throw new MiExcepcion(mensajeError);
        }
    }
}
