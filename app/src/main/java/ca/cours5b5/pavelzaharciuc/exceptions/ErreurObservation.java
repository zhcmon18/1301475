package ca.cours5b5.pavelzaharciuc.exceptions;


import java.util.Map;

public class ErreurObservation extends RuntimeException {

    public ErreurObservation(Exception e){
        super(e);
    }

    public ErreurObservation(String message){
        super(message);
    }

}
