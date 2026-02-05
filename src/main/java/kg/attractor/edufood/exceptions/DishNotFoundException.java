package kg.attractor.edufood.exceptions;

import java.util.NoSuchElementException;

public class DishNotFoundException extends NoSuchElementException {
    public DishNotFoundException() {
        super("Dish not found!");
    }
}
