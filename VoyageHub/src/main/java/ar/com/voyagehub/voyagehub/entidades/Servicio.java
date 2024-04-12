package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Servicio extends Producto {
    private String descripcionBreve;
    private String destinoServicio;
    @Column(columnDefinition = "DATE")
    private LocalDate fechaServicio;
    private Float costoServicio;

}

