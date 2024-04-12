package ar.com.voyagehub.voyagehub.entidades;

import jakarta.persistence.*;

@Entity
public class Empleado extends Cliente {
    private String cargo;
    private Float sueldo;

}
