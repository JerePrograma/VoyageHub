package ar.com.voyagehub.voyagehub.controladores;

import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/")
    public String usuarioList(Model model) {
        return "usuario_list.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "cliente_form.html";
    }

    @PostMapping("/registro")
    public String registro(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("contrasenia") String contrasenia,
            @RequestParam("contrasenia2") String contrasenia2,
            @RequestParam("direccion") String direccion,
            @RequestParam("dni") String dni,
            @RequestParam("fechaNac") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaNac, // Asegúrate que la entrada del formulario sea en este formato
            @RequestParam("nacionalidad") String nacionalidad,
            @RequestParam("celular") String celular,
            RedirectAttributes redirectAttributes) {
        try {
            clienteServicio.crearCliente(nombre, apellido, email, contrasenia, contrasenia2, direccion, dni, fechaNac, nacionalidad, celular);
            redirectAttributes.addFlashAttribute("exito", "Usuario registrado correctamente");
            return "redirect:/login";
        } catch (MiExcepcion e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("nombre", nombre);
            redirectAttributes.addFlashAttribute("apellido", apellido);
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("direccion", direccion);
            redirectAttributes.addFlashAttribute("dni", dni);
            redirectAttributes.addFlashAttribute("nacionalidad", nacionalidad);
            redirectAttributes.addFlashAttribute("celular", celular);

            return "redirect:/cliente/registrar";
        }
    }
}
