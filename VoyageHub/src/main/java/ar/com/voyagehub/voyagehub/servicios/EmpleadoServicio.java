package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Empleado;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.repositorios.EmpleadoRepositorio;
import jakarta.servlet.http.HttpSession;
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
public class EmpleadoServicio implements UserDetailsService {

    private final EmpleadoRepositorio empleadoRepositorio;

    public EmpleadoServicio(EmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    public void crearEmpleado(String nombre,
                              String apellido,
                              String email,
                              String contrasenia,
                              String contrasenia2,
                              String direccion,
                              String dni,
                              LocalDate fechaNac,
                              String nacionalidad,
                              String celular,
                              String cargo,
                              Float sueldo) throws MiExcepcion {
        validarDatos(nombre, apellido, email, contrasenia, contrasenia2, direccion, dni, fechaNac, nacionalidad, celular, cargo);
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEmail(email);
        empleado.setContrasenia(new BCryptPasswordEncoder().encode(contrasenia));
        empleado.setDireccion(direccion);
        empleado.setDni(dni);
        empleado.setFechaNac(fechaNac);
        empleado.setNacionalidad(nacionalidad);
        empleado.setCelular(celular);
        empleado.setCargo(cargo);
        empleado.setSueldo(sueldo);
        empleado.setRol(Rol.EMPLEADO);
        empleadoRepositorio.save(empleado);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Empleado empleado = empleadoRepositorio.buscarPorEmail(email);

        if (empleado == null) {
            throw new UsernameNotFoundException("Cliente no encontrado con el email: " + email);
        }

        List<GrantedAuthority> permisos = new ArrayList<>();
        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + empleado.getRol().toString());
        permisos.add(p);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("Empleadosession", empleado);
        return new User(empleado.getEmail(), empleado.getContrasenia(), permisos);
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
                             String celular,
                             String cargo) throws MiExcepcion {

        validarCadena(nombre, "El nombre no puede ser vacío o nulo.");
        validarCadena(apellido, "El apellido no puede ser vacío o nulo.");
        validarCadena(email, "El email no puede ser vacío o nulo.");
        validarCadena(direccion, "La dirección no puede ser vacía o nula.");
        validarCadena(nacionalidad, "La nacionalidad no puede ser vacía o nula.");
        validarCadena(cargo, "El cargo no puede ser vacío o nulo");
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
}
