package it.epicode.pizzeria.entity;

import it.epicode.pizzeria.enumeration.statoOrdine;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@jakarta.persistence.Table(name = "custom_order")
@NamedQuery(name = "Trova_tutto_Order", query = "SELECT a FROM Order a")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pizze")
    private List<Pizza> pizzas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "toppings")
    private List<Toppings> toppings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "drinks")
    private List<Drinks> drinks = new ArrayList<>();

    @ManyToOne
    private Table table;

    @Enumerated
    @Column(name = "stato_ordine")
    private it.epicode.pizzeria.enumeration.statoOrdine statoOrdine;

    @Column(name = "numero_coperti")
    private Integer numeroCoperti;

    @Column(name = "ora_aquisizione")
    private LocalDate oraAquisizione;

    //    totale costo dei suoi elementi e coperti
    @Column(name = "tot_costo")
    private double totCosto;

}
