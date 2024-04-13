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
public class Cliente extends Usuario {
    private String direccion;
    private String dni;
    @Column(columnDefinition = "DATE")
    private LocalDate fechaNac;
    private String nacionalidad;
    private String celular;

}
