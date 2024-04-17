package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.entidades.Servicio;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.repositorios.ServicioRepositorio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ServicioServicio {

    @Autowired
    ServicioRepositorio servicioRepositorio;

    public void crearServicio(Long codigoServicio,
                              String nombre,
                              String descripcionBreve,
                              String destinoServicio,
                              LocalDate fechaServicio,
                              Float costoServicio
    ) throws MiExcepcion {
        validarDatos(codigoServicio, nombre, descripcionBreve, destinoServicio, fechaServicio, costoServicio);
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setDescripcionBreve(descripcionBreve);
        servicio.setDestinoServicio(destinoServicio);
        servicio.setFechaServicio(fechaServicio);
        servicio.setCostoServicio(costoServicio);
        servicioRepositorio.save(servicio);
    }

    void validarCadena(String valor, String mensajeError) throws MiExcepcion {
        if (valor == null || valor.trim().isEmpty()) {
            throw new MiExcepcion(mensajeError);
        }
    }

    private void validarDatos(Long codigoServicio,
                              String nombre,
                              String descripcionBreve,
                              String destinoServicio,
                              LocalDate fechaServicio,
                              Float costoServicio) {
    }
}

