package kg.attractor.edufood.exceptions;

import java.util.NoSuchElementException;

public class RoleNotFoundException extends NoSuchElementException {
    public RoleNotFoundException() {
        super("Role not found!");
    }
}
