package it.epicode.pizzeria.pizza_test;

import it.epicode.pizzeria.entity.Pizza;
import it.epicode.pizzeria.repo.MenuRepository;
import it.epicode.pizzeria.repo.PizzaRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class PizzaTest {

    @Autowired
    PizzaRepo pizzaRepo;

    @Disabled
    @Test
    @DisplayName("Test per il salvataggio di una pizza")
    public void testSalvataggioPizza() {

        long pizzeGiaSalvate = pizzaRepo.count();
        Pizza pizzaTest = new Pizza();
        pizzaTest.setName("margherita");
        pizzaRepo.save(pizzaTest);
        long pizzeSalvateC = pizzaRepo.count();

        assertEquals(pizzeGiaSalvate + 1L, pizzeSalvateC);

        pizzaRepo.delete(pizzaTest);
    }

    @Disabled
    @ParameterizedTest
    @DisplayName("Test per il salvataggio di una pizza parametrico")
    @ValueSource(strings = {"margherita", "trimone", "foata"})
    public void testSalvataggioPizzaParametrico(String nomePizza) {

        long pizzeGiaSalvate = pizzaRepo.count();
        Pizza pizzaTest = new Pizza();
        pizzaTest.setName(nomePizza);
        pizzaRepo.save(pizzaTest);
        long pizzeSalvateC = pizzaRepo.count();

        assertEquals(pizzeGiaSalvate + 1L, pizzeSalvateC);


        long pizzaID = pizzaTest.getId();
        var pizzaSalvata = pizzaRepo.findById(pizzaID);

        assertEquals(pizzaTest.getName(), pizzaSalvata.get().getName());

        pizzaRepo.delete(pizzaTest);
    }

    @Test
    @Transactional
    @Rollback // Annota per eseguire il rollback
    public void testSalvataggioERollback() {

        long countBefore = pizzaRepo.count();

        Pizza pizza = new Pizza();
        pizza.setName("Test Rollback");
        pizza.setPrice(12.5);
        pizzaRepo.save(pizza);

        long countAfterSave = pizzaRepo.count();
        assertEquals(countBefore + 1, countAfterSave);

        
    }

}
