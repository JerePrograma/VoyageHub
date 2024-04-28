package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Servicio;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.repositorios.ServicioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServicio {

    final
    ServicioRepositorio servicioRepositorio;

    public ServicioServicio(ServicioRepositorio servicioRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
    }

    public void crearServicio(String nombre,
                              String descripcionBreve,
                              String destinoServicio,
                              LocalDate fechaServicio,
                              Float costoServicio
    ) throws MiExcepcion {
        validarDatos(nombre, descripcionBreve, destinoServicio, fechaServicio, costoServicio);
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

    private void validarDatos(String nombre,
                              String descripcionBreve,
                              String destinoServicio,
                              LocalDate fechaServicio,
                              Float costoServicio) throws MiExcepcion {
        validarCadena(descripcionBreve, "La descripción no puede estar vacío ni ser nulo");
        validarCadena(destinoServicio, "El destino no puede estar vacío ni ser nulo");
        validarCadena(nombre, "El nombre no puede estar vacío ni ser nulo");
    }

    @Transactional(readOnly = true)
    public Optional<Servicio> getOne(Long idServicio) {
        return servicioRepositorio.findById(idServicio);
    }

    @Transactional(readOnly = true)
    public List<Servicio> listarServicios() {
        return servicioRepositorio.findAll();
    }

    @Transactional
    public void eliminarServicio(Long idServicio) {
        Optional<Servicio> respuesta = getOne(idServicio);
        if (respuesta.isPresent()) {
            Servicio servicio = respuesta.get();

            servicioRepositorio.delete(servicio);
        }
    }
}

