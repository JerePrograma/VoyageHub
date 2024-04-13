package ar.com.voyagehub.voyagehub.repositorios;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

//    boolean existsByDNI(String dni);
}
