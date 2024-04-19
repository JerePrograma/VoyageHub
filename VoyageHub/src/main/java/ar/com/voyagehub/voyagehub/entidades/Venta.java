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

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "paquete_id")
    private Paquete paquete;

    @Column(name = "fecha_de_venta")
    private LocalDateTime fechaVenta;
    @Column(name = "medio_de_pago")
    private String medioPago;
}
