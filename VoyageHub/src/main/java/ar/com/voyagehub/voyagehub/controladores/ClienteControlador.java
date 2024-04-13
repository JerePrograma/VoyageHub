package ar.com.voyagehub.voyagehub.controladores;

import ar.com.voyagehub.voyagehub.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            @RequestParam("direccion") String direccion,
            @RequestParam("dni") String dni,
            @RequestParam("fechaNac") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaNac, // Asegúrate que la entrada del formulario sea en este formato
            @RequestParam("nacionalidad") String nacionalidad,
            @RequestParam("celular") String celular) {
        try {
            clienteServicio.crearCliente(nombre, apellido, email, contrasenia, direccion, dni, fechaNac, nacionalidad, celular);
            return "index.html";
        } catch (Exception e) {
            return "index.html";
        }
    }


}
