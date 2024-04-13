package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Usuario;
import ar.com.voyagehub.voyagehub.enums.Rol;
import ar.com.voyagehub.voyagehub.repositorios.UsuarioRepositorio;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public void crearUsuario(String nombre,
                             String apellido,
                             String email,
                             String contrasenia) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setRol(Rol.ADMIN);
        usuarioRepositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario Usuario = usuarioRepositorio.buscarPorEmail(email);

        if (Usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            // Crea una lista de permisos (GrantedAuthority) asociados al Usuario


            // Agrega un permiso correspondiente al rol del Usuario. Este permiso
            // se crea con el prefijo "ROLE_" seguido del nombre del rol del Usuario.
            // Por ejemplo, si el rol del Usuario es "ADMIN", el permiso será
            // "ROLE_ADMIN".
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + Usuario.getRol().toString());
            permisos.add(p);

            // Devuelve un objeto UserDetails que representa al Usuario. Este
            // objeto contiene el correo electrónico del Usuario, su contraseña (ya
            // codificada) y la lista de permisos asociados.
            return new User(Usuario.getEmail(), Usuario.getContrasenia(), permisos);
        } else {
            // Si no se encuentra ningún Usuario con el correo electrónico proporcionado,
            // devuelve null.

            return null;
        }
    }
}
