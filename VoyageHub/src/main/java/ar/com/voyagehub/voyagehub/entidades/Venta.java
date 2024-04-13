package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numVenta;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaVenta;
    private String medioPago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
