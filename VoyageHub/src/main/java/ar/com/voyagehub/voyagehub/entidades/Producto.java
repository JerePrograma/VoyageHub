package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// O puedes usar SINGLE_TABLE si prefieres una única tabla con discriminador
@DiscriminatorColumn(name = "tipo_producto")
@Data
@NoArgsConstructor
public abstract class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false)
    private String nombre;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
