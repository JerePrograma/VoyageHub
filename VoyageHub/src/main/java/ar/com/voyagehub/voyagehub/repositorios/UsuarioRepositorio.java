package ar.com.voyagehub.voyagehub.repositorios;

import ar.com.voyagehub.voyagehub.entidades.Usuario;
import ar.com.voyagehub.voyagehub.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email =  :email")
    public Usuario buscarPorEmail(@Param("email") String email);

//    @Query("SELECT u FROM Usuario u WHERE u.resetPwToken =  :resetPwToken")
//    public Usuario buscarPorResetPwToken(@Param("resetPwToken") String resetPwToken);

//    @Query("SELECT u FROM Usuario u WHERE u.idUsuario =  : idUsuario")
//    public Usuario buscarPorIdCodigoTributario(@Param("idUsuario") String idUsuario);

}
