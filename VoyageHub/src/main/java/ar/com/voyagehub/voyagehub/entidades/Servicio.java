package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Servicio extends Producto {
    private String descripcionBreve;
    private String destinoServicio;
    @Column(columnDefinition = "DATE")
    private LocalDate fechaServicio;
    private Float costoServicio;

}

