package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    @Column(columnDefinition = "DATE")
    private LocalDate fechaNac;
    private String nacionalidad;
    private String celular;
    private String email;

}
