package it.epicode.pizzeria.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.List;


@Getter
@Setter
@Data
@Entity
@NamedQuery(name = "Trova_tutto_Pizza", query = "SELECT a FROM Pizza a")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "price")
    private Double price;

    @Column(name = "ingredients")
    private String ingredients;


    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", price=" + price +
                ", ingredients='" + ingredients + '\'' +
                ", menu=" + menu.getId() +
                '}';
    }
}
