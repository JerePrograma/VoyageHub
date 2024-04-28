package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Paquete;
import ar.com.voyagehub.voyagehub.entidades.Servicio;
import ar.com.voyagehub.voyagehub.repositorios.PaqueteRepositorio;
import ar.com.voyagehub.voyagehub.repositorios.ServicioRepositorio;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data

public class PaqueteServicio {

    private final PaqueteRepositorio paqueteRepositorio;
    private final ServicioRepositorio servicioRepositorio;

    public PaqueteServicio(PaqueteRepositorio paqueteRepositorio, ServicioRepositorio servicioRepositorio) {
        this.paqueteRepositorio = paqueteRepositorio;
        this.servicioRepositorio = servicioRepositorio;
    }

    public Paquete crearPaquete(Paquete paquete, List<Long> serviciosIds) {
        List<Servicio> serviciosSeleccionados = servicioRepositorio.findAllById(serviciosIds);
        paquete.setServiciosIncluidos(serviciosSeleccionados);
        return paqueteRepositorio.saveAndFlush(paquete);
    }


    public void actualizarPaquete(Paquete paquete, List<Long> serviciosIds) {
        List<Servicio> serviciosSeleccionados = servicioRepositorio.findAllById(serviciosIds);
        paquete.setServiciosIncluidos(serviciosSeleccionados);
        paqueteRepositorio.save(paquete);
    }

    @Transactional(readOnly = true)
    public Optional<Paquete> getOne(Long idServicio) {
        return paqueteRepositorio.findById(idServicio);
    }

    @Transactional(readOnly = true)
    public List<Paquete> listarPaquetes() {
        return paqueteRepositorio.findAll();
    }

    @Transactional
    public void eliminarPaquete(Long idPaquete) {
        Optional<Paquete> respuesta = getOne(idPaquete);
        if (respuesta.isPresent()) {
            Paquete paquete = respuesta.get();

            paqueteRepositorio.delete(paquete);
        }
    }
}
