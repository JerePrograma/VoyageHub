package ar.com.voyagehub.voyagehub.repositorios;

import ar.com.voyagehub.voyagehub.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
}
