package ca.cours5b5.pavelzaharciuc.exceptions;

public class ErreurSerialisation extends RuntimeException {

    public ErreurSerialisation(Exception e) {
        super(e);
    }
}
