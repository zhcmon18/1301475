package ca.cours5b5.pavelzaharciuc.controleurs.interfaces;


public abstract class ListenerFournisseur {

    public abstract void executer(Object... args);

    public boolean actionExecutable(Object... args) {
        return true;
    }
}
