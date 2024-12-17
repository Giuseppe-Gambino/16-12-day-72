package it.epicode.pizzeria.entity;

import it.epicode.pizzeria.enumeration.Stato;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@jakarta.persistence.Table(name = "tavolo")
@NamedQuery(name = "Trova_tutto_Table", query = "SELECT a FROM Table a")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "numero_coperti_massimo")
    private Integer numeroCopertiMassimo;

    @Enumerated
    @Column(name = "stato")
    private Stato stato;

}
