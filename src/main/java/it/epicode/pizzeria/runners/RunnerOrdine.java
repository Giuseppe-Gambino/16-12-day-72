package it.epicode.pizzeria.runners;

import it.epicode.pizzeria.configurations.AppConfig;
import it.epicode.pizzeria.entity.*;
import it.epicode.pizzeria.enumeration.Stato;
import it.epicode.pizzeria.enumeration.statoOrdine;
import it.epicode.pizzeria.repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;


@Component
@RequiredArgsConstructor
@org.springframework.core.annotation.Order(2)
public class RunnerOrdine implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RunnerOrdine.class);

    private final Menu menu;
    private final PizzaRepo pizzaRepo;
    private final ToppingsRepository toppingsRepo;
    private final DrinksRepository drinksRepo;
    private final OrderRepository orderRepo;
    private final TableRepository tableRepo;


    @Value("${application.coperto}")
    double costoCoperto;

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        // Recuperiamo il bean Menu
        menu.printMenu();


        logger.info("sto creando l'ordine");


        Table table = new Table();
        table.setStato(Stato.LIBERO);
        table.setNumeroCopertiMassimo(30);
        tableRepo.save(table);


        Order order = new Order();
        order.setPizzas(Arrays.asList(pizzaRepo.findAll().get(0), pizzaRepo.findAll().get(1)));
        order.setDrinks(Arrays.asList(drinksRepo.findAll().get(2), drinksRepo.findAll().get(1)));
        order.setToppings(Arrays.asList(toppingsRepo.findAll().get(3)));
        order.setStatoOrdine(statoOrdine.IN_CORSO);
        order.setOraAquisizione(LocalDate.now());
        order.setTable(table);
        order.setNumeroCoperti(12);
        double costoCoperti = costoCoperto * order.getNumeroCoperti();
        double costoPizze = order.getPizzas().stream()
                .mapToDouble(Pizza::getPrice)
                .min().getAsDouble();

        double costoDrinks = order.getDrinks().stream().mapToDouble(Drinks::getPrice)
                .min().getAsDouble();

        double costoTop = order.getToppings().stream().mapToDouble(Toppings::getPrice)
                .min().getAsDouble();

        double tot = costoCoperti + costoPizze + costoDrinks + costoTop;

        order.setTotCosto(tot);

        orderRepo.save(order);

        logger.info(String.valueOf(order));
        logger.info("ordine creato");

        System.out.println(pizzaRepo.findByPriceLessThan(6.49));


    }
}
