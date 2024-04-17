package ar.com.voyagehub.voyagehub.servicios;


import ar.com.voyagehub.voyagehub.entidades.Empleado;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.excepciones.MiExcepcion;
import ar.com.voyagehub.voyagehub.repositorios.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmpleadoServicio  {

    @Autowired
    ClienteRepositorio clienteRepositorio;
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
                             Float sueldo
    ) throws MiExcepcion {
        validarDatos(nombre, apellido, email, contrasenia, contrasenia2, direccion, dni, fechaNac, nacionalidad, celular);
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEmail(email);
        empleado.setContrasenia(new BCryptPasswordEncoder().encode(contrasenia));
        empleado.setDireccion(direccion);
        empleado.setFechaNac(fechaNac);
        empleado.setCelular(celular);
        empleado.setNacionalidad(nacionalidad);
        empleado.setDni(dni);
        empleado.setRol(Rol.EMPLEADO);
        empleado.setCargo(cargo);
        empleado.setSueldo(sueldo);
        clienteRepositorio.save(empleado);
    }
}
