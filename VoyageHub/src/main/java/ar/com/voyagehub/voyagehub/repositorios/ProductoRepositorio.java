package ar.com.voyagehub.voyagehub.repositorios;

import ar.com.voyagehub.voyagehub.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
