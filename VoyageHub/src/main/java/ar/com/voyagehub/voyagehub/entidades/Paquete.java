package ar.com.voyagehub.voyagehub.entidades;

import ar.com.voyagehub.voyagehub.entidades.Servicio;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoPaquete;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "paquete_servicios",
            joinColumns = @JoinColumn(name = "codigo_paquete"),
            inverseJoinColumns = @JoinColumn(name = "codigo_servicio")
    )
    private List<Servicio> serviciosIncluidos = new ArrayList<>();

    public Float getCostoPaquete() {
        if (serviciosIncluidos != null && !serviciosIncluidos.isEmpty()) {
            float sumaCostos = serviciosIncluidos.stream()
                    .map(Servicio::getCostoServicio)
                    .reduce(0f, Float::sum);
            return sumaCostos - sumaCostos * 0.10f; // Aplicar el descuento del 10%
        }
        return 0f;
    }
}