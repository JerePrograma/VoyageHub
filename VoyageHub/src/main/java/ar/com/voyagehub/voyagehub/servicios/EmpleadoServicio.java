package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Empleado;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.repositorios.ClienteRepositorio;

import ar.com.voyagehub.voyagehub.repositorios.EmpleadoRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Optional<Empleado> getOneEmpleado(Long idEmpleado) {
        return empleadoRepositorio.findById(idEmpleado);
    }

    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        return empleadoRepositorio.findAll();
    }
    @Transactional
    public void eliminarEmpleado(Long idEmpleado) {
        Optional<Empleado> respuesta = getOneEmpleado(idEmpleado);
        if (respuesta.isPresent()) {
            Empleado empleado = respuesta.get();

            empleadoRepositorio.delete(empleado);
        }
    }
}
