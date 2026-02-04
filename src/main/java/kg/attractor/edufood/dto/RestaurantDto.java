package kg.attractor.edufood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantDto {
    private Integer id;
    private String name;
    private String description;
}
