package ar.com.voyagehub.voyagehub.controladores;

import ar.com.voyagehub.voyagehub.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/")
    public String index() {
    return "index.html";
    }
}
