package kg.attractor.edufood.repository;

import kg.attractor.edufood.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Page<Restaurant> findRestaurantsByNameContaining(String name, Pageable pageable);
}
