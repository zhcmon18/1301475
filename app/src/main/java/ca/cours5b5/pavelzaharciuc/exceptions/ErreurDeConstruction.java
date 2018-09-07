package ca.cours5b5.pavelzaharciuc.exceptions;

public class ErreurDeConstruction extends RuntimeException {

    public ErreurDeConstruction(Exception e) {
        super(e);
    }
}
