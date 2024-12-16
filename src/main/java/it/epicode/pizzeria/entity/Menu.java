package it.epicode.pizzeria.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Data
@Entity
@NamedQuery(name = "Trova_tutto_Menu", query = "SELECT a FROM Menu a")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ElementCollection
    @Column(name = "pizze")
    private List<Pizza> pizze = new ArrayList<>();

    @ElementCollection
    @Column(name = "toppings")
    private List<Toppings> toppings = new ArrayList<>();

    @ElementCollection
    @Column(name = "drinks")
    private List<Drinks> drinks = new ArrayList<>();


    @Override
    public String toString() {
        return "Menu " +
                ", pizze=" + pizze +
                ", toppings=" + toppings +
                ", drinks=" + drinks +
                '}';
    }

    public void printMenu() {
        System.out.println("### PIZZAS ###");
        pizze.forEach(System.out::println);

        System.out.println("\n### TOPPINGS ###");
        toppings.forEach(System.out::println);

        System.out.println("\n### DRINKS ###");
        drinks.forEach(System.out::println);
    }
}
