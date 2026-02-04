package kg.attractor.edufood.repository;

import kg.attractor.edufood.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    Page<Dish> findDishesByRestaurant_Id(Integer restaurantId, Pageable pageable);
}
