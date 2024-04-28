package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.entidades.Empleado;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.repositorios.ClienteRepositorio;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio implements UserDetailsService {
    final
    ClienteRepositorio clienteRepositorio;

    public ClienteServicio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }


    public void crearCliente(String nombre,
                             String apellido,
                             String email,
                             String contrasenia,
                             String contrasenia2,
                             String direccion,
                             String dni,
                             LocalDate fechaNac,
                             String nacionalidad,
                             String celular) throws MiExcepcion {
        validarDatos(nombre, apellido, email, contrasenia, contrasenia2, direccion, dni, fechaNac, nacionalidad, celular);
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
        cliente.setRol(Rol.CLIENTE);
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

    public void validarDatos(String nombre,
                             String apellido,
                             String email,
                             String contrasenia,
                             String contrasenia2,
                             String direccion,
                             String dni,
                             LocalDate fechaNac,
                             String nacionalidad,
                             String celular) throws MiExcepcion {

        validarCadena(nombre, "El nombre no puede ser vacío o nulo.");
        validarCadena(apellido, "El apellido no puede ser vacío o nulo.");
        validarCadena(email, "El email no puede ser vacío o nulo.");
        validarCadena(direccion, "La dirección no puede ser vacía o nula.");
        validarCadena(nacionalidad, "La nacionalidad no puede ser vacía o nula.");
        validarCadena(celular, "El celular no puede ser vacío o nulo.");
        validarFecha(fechaNac);

        validarContrasenia(contrasenia);
        if (!contrasenia.equals(contrasenia2)) {
            throw new MiExcepcion("Las contraseñas ingresadas deben ser iguales");
        }
        if (dni == null) {
            throw new MiExcepcion("El DNI no puede estar vacío y debe contener solo números (sin puntos), <br> y debe ser de al menos 7 dígitos.");
        } else if (!dni.matches("\\d{7,9}")) {
            throw new MiExcepcion("El DNI debe ser de al menos 7 dígitos y debe contener solo números (sin puntos).");
        }
    }

    void validarCadena(String valor, String mensajeError) throws MiExcepcion {
        if (valor == null || valor.trim().isEmpty()) {
            throw new MiExcepcion(mensajeError);
        }
    }

    private void validarFecha(LocalDate fecha) throws MiExcepcion {
        if (fecha == null) {
            throw new MiExcepcion("La fecha de nacimiento no puede ser nula.");
        }
    }

    public void validarContrasenia(String contrasenia) throws MiExcepcion {
        if (contrasenia == null || contrasenia.length() <= 5) {
            throw new MiExcepcion("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
    }
    @Transactional(readOnly = true)
    public Optional<Cliente> getOne(Long idCliente) {
        return clienteRepositorio.findById(idCliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();
    }
    @Transactional
    public void eliminarCliente(Long idCliente) {
        Optional<Cliente> respuesta = getOne(idCliente);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();

            clienteRepositorio.delete(cliente);
        }
    }
//    @Transactional
//    public void darBajaCliente(Long idCliente) {
//        Optional<Cliente> respuesta = getOne(idCliente);
//        if (respuesta.isPresent()) {
//            Cliente cliente = respuesta.get();
//            cliente.setAlta(false);
//
//            clienteRepositorio.save(cliente);
//        }
//    }

//    @Transactional
//    public void darAltaCliente(String idCodigoTributario) {
//        Optional<Cliente> respuesta = ClienteRepositorio.findById(idCodigoTributario);
//        if (respuesta.isPresent()) {
//            Cliente cliente = respuesta.get();
//            cliente.setAlta(true);
//
//            clienteRepositorio.save(cliente);
//        }
//    }
//    public boolean existeClienteConDNI(String DNI) {
//        // Implementa la lógica para buscar un Cliente por su DNI en la base de datos
//        // Retorna true si el Cliente con ese DNI existe, de lo contrario, retorna false
//        return ClienteRepositorio.existsByDNI(DNI); // Ajusta según tu modelo y repositorio
//    }

//    @Transactional(readOnly = true)
//    public Cliente obtenerClientePorUsername(String username) throws MiExcepcion {
//        Cliente Cliente = ClienteRepositorio.buscarPorEmail(username);
//        if (Cliente == null) {
//            throw new MiExcepcion("No se encontró un Cliente con el username: " + username);
//        }
//        return Cliente;
//    }
}
