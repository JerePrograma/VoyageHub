package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoServicio;

    private String nombre;
    private String descripcionBreve;
    private String destinoServicio;
    @Column(columnDefinition = "DATE")
    private LocalDate fechaServicio;
    private Float costoServicio;

}