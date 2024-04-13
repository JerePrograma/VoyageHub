package ar.com.voyagehub.voyagehub.entidades;

import ar.com.voyagehub.voyagehub.enums.Rol;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    @Enumerated(EnumType.STRING)
    private Rol rol;
}

