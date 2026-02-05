package kg.attractor.edufood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DishDto {

    private Integer id;
    private String name;
    private Double price;
    private String restaurantName;
}
