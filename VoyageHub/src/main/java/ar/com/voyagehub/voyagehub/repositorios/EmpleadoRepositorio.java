package ar.com.voyagehub.voyagehub.repositorios;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
    @Query("SELECT e FROM Empleado e WHERE e.email =  :email")
    public Empleado buscarPorEmail(@Param("email") String email);

//    @Query("SELECT u FROM Usuario u WHERE u.resetPwToken =  :resetPwToken")
//    public Usuario buscarPorResetPwToken(@Param("resetPwToken") String resetPwToken);

//    @Query("SELECT u FROM Usuario u WHERE u.idUsuario =  : idUsuario")
//    public Usuario buscarPorIdCodigoTributario(@Param("idUsuario") String idUsuario);

//    boolean existsByDNI(String dni);
}
