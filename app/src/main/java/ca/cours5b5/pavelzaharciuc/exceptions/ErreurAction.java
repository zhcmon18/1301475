package ca.cours5b5.pavelzaharciuc.exceptions;

public class ErreurAction extends RuntimeException {

    public ErreurAction(String message) {}

    public ErreurAction(Exception e) {}
}
