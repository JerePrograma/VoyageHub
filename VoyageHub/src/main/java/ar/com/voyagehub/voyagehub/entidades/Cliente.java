package ar.com.voyagehub.voyagehub.entidades;

import ar.com.voyagehub.voyagehub.enums.Rol;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String direccion;
    private String dni;
    private LocalDate fechaNac;
    private String nacionalidad;
    private String celular;

    public Cliente(String nombre, String apellido, String email, String contrasenia, String direccion, String dni, LocalDate fechaNac, String nacionalidad, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.nacionalidad = nacionalidad;
        this.celular = celular;
    }
}
