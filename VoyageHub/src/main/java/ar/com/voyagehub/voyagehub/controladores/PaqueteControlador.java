package ar.com.voyagehub.voyagehub.controladores;

import ar.com.voyagehub.voyagehub.entidades.Paquete;
import ar.com.voyagehub.voyagehub.entidades.Servicio;
import ar.com.voyagehub.voyagehub.servicios.PaqueteServicio;
import ar.com.voyagehub.voyagehub.servicios.ServicioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/paquete")
public class PaqueteControlador {

    @Autowired
    private PaqueteServicio paqueteServicio;

    @Autowired
    private ServicioServicio servicioServicio;

    // Mostrar el formulario para crear un nuevo paquete
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        List<Servicio> todosLosServicios = servicioServicio.listarServicios();
        model.addAttribute("paquete", new Paquete());
        model.addAttribute("todosLosServicios", todosLosServicios);
        return "paquete_form.html";
    }

    // Procesar el formulario para crear un nuevo paquete
    @PostMapping("/crear")
    public String crearPaquete(@ModelAttribute Paquete paquete, @RequestParam List<Long> serviciosIncluidos, RedirectAttributes redirectAttributes) {
        paqueteServicio.crearPaquete(paquete, serviciosIncluidos);
        redirectAttributes.addFlashAttribute("exito", "Paquete creado con éxito");
        return "redirect:/paquete/lista"; // Redirige a la lista de paquetes después de crear
    }

    @GetMapping("/editar/{id}")
    public String editarPaquete(@PathVariable Long id, Model model) {
        Optional<Paquete> paquete = paqueteServicio.getOne(id);
        List<Servicio> todosLosServicios = servicioServicio.listarServicios();
        model.addAttribute("paquete", paquete);
        model.addAttribute("todosLosServicios", todosLosServicios);
        return "paquete_form.html";
    }

    @PostMapping("/update")
    public String actualizarPaquete(@ModelAttribute Paquete paquete, @RequestParam List<Long> serviciosIncluidos) {
        paqueteServicio.actualizarPaquete(paquete, serviciosIncluidos);
        return "redirect:/paquete/lista";
    }
}
