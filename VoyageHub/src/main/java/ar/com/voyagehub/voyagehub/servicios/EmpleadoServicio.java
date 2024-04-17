package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.entidades.Empleado;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.repositorios.ClienteRepositorio;
import ar.com.voyagehub.voyagehub.repositorios.EmpleadoRepositorio;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoServicio extends ClienteServicio {

    private final EmpleadoRepositorio empleadoRepositorio;

    public EmpleadoServicio(EmpleadoRepositorio empleadoRepositorio, ClienteRepositorio clienteRepositorio) {
        super(clienteRepositorio);
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
        super.validarDatos(nombre, apellido, email, contrasenia, contrasenia2, direccion, dni, fechaNac, nacionalidad, celular);
        super.validarCadena(cargo, "El cargo no puede ser vacío o nulo");
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
