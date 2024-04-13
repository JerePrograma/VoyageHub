package ar.com.voyagehub.voyagehub.entidades;

import ar.com.voyagehub.voyagehub.enums.Rol;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
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

}
