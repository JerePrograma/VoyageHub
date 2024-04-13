package ar.com.voyagehub.voyagehub.controladores;

import ar.com.voyagehub.voyagehub.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index() {
        usuarioServicio.crearUsuario("Jeremias", "Rivelli", "jeremias.j.riv@gmail.com", "prueba");
        return "index.html";
    }
}
