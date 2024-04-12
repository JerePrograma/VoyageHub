package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Paquete extends Producto {
    @ManyToMany
    @JoinTable(
            name = "paquete_servicios",
            joinColumns = @JoinColumn(name = "paquete_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private List<Servicio> serviciosIncluidos;

    private Float costoPaquete;

    // Getters y setters
}


