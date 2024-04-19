package ar.com.voyagehub.voyagehub.controladores;

import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.servicios.ServicioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/servicio")
public class ServicioControlador {
    @Autowired
    ServicioServicio servicioServicio;

    @GetMapping("/")
    public String usuarioList(Model model) {
        return "servicio_list.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "servicio_form";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam("codigoServicio") Long codigoServicio,
                           @RequestParam("nombre") String nombre,
                           @RequestParam("descripcionBreve") String descripcionBreve,
                           @RequestParam("destinoServicio") String destinoServicio,
                           @RequestParam("fechaServicio") LocalDate fechaServicio,
                           @RequestParam("costoServicio") Float costoServicio,
                           RedirectAttributes redirectAttributes) {
        try {
            servicioServicio.crearServicio(codigoServicio, nombre, descripcionBreve, destinoServicio, fechaServicio, costoServicio);
            redirectAttributes.addFlashAttribute("exito", "Servicio registrado correctamente");
            return "redirect:/";
        } catch (MiExcepcion e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("nombre", nombre);
            redirectAttributes.addFlashAttribute("descripcionBreve", descripcionBreve);
            redirectAttributes.addFlashAttribute("destinoServicio", destinoServicio);
            redirectAttributes.addFlashAttribute("fechaServicio", fechaServicio);
            redirectAttributes.addFlashAttribute("costoServicio", costoServicio);
            return "redirect:/servicio/registrar";
        }
    }
}
