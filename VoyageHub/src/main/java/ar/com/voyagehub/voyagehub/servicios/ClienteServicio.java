package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.repositorios.ClienteRepositorio;
import ar.com.voyagehub.voyagehub.repositorios.ClienteRepositorio;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServicio implements UserDetailsService {
    @Autowired
    ClienteRepositorio clienteRepositorio;


    public void crearCliente(String nombre,
                             String apellido,
                             String email,
                             String contrasenia,
                             String direccion,
                             String dni,
                             LocalDate fechaNac,
                             String nacionalidad,
                             String celular) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setContrasenia(new BCryptPasswordEncoder().encode(contrasenia));
        cliente.setDireccion(direccion);
        cliente.setFechaNac(fechaNac);
        cliente.setCelular(celular);
        cliente.setNacionalidad(nacionalidad);
        cliente.setDni(dni);
        cliente.setRol(Rol.ADMIN);
        clienteRepositorio.save(cliente);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente Cliente = clienteRepositorio.buscarPorEmail(email);

        if (Cliente == null) {
            throw new UsernameNotFoundException("Cliente no encontrado con el email: " + email);
        }

        List<GrantedAuthority> permisos = new ArrayList<>();
        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + Cliente.getRol().toString());
        permisos.add(p);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("Clientesession", Cliente);
        return new User(Cliente.getEmail(), Cliente.getContrasenia(), permisos);
    }
}
