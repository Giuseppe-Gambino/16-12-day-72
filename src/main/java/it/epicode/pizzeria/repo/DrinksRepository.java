package it.epicode.pizzeria.repo;

import it.epicode.pizzeria.entity.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinksRepository extends JpaRepository<Drinks, Long> {
}