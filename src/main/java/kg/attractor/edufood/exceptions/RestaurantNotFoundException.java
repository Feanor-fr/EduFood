package kg.attractor.edufood.exceptions;

import java.util.NoSuchElementException;

public class RestaurantNotFoundException extends NoSuchElementException {
    public RestaurantNotFoundException() {
        super("Restaurant not found!");
    }
}
