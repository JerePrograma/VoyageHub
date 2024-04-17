package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Empleado extends Cliente {
    private String cargo;
    private Float sueldo;

}

