package ca.cours5b5.pavelzaharciuc.exceptions;

public class ErreurDeSerialisation extends RuntimeException {

    public ErreurDeSerialisation (Exception e) {
        super(e);
    }
}
