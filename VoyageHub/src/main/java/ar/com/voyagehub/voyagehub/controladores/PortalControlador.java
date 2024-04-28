package ar.com.voyagehub.voyagehub.controladores;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.repositorios.ClienteRepositorio;
import ar.com.voyagehub.voyagehub.servicios.ClienteServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteServicio clienteServicio;


    @GetMapping("/")
    public String index(ModelMap modelo, HttpSession session) {

        Cliente cliente = (Cliente) session.getAttribute("usuariosession");
        if(cliente != null){
            modelo.put("cliente", cliente);
        }
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "cliente o Contraseña inválidos!");
        }
        return "login.html";
    }

//    @PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_EMPLEADO', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuariosession");
        if (cliente == null) {
            return "redirect:/login";
        }

        modelo.put("cliente", cliente);
        if (cliente.getRol().toString().equals("ADMIN")) {
            return "redirect:/";
        }
        return "redirect:/cliente/perfil/" + cliente.getIdUsuario();
    }


}
