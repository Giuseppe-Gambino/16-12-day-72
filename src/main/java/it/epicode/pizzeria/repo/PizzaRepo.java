package it.epicode.pizzeria.repo;

import it.epicode.pizzeria.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Long> {


    List<Pizza> findByName(String name);

    List<Pizza> findByPriceLessThan(double val);

}