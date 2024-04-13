package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
public class Empleado extends Usuario {
    private String cargo;
    private Float sueldo;

}

