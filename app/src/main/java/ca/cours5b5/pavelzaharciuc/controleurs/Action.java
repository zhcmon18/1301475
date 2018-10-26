package ca.cours5b5.pavelzaharciuc.controleurs;


import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;

public class Action {

    Fournisseur fournisseur;

    ListenerFournisseur listenerFournisseur;

    Object[] args;

    public void setArguments(Object... args) {
        this.args = args;
    }

    public void executerDesQuePossible(){
        ControleurAction.executerDesQuePossible(this);
    }

    Action cloner(){

        Action clone = new Action();

        clone.fournisseur = fournisseur;
        clone.listenerFournisseur = listenerFournisseur;

        clone.args = (args == null) ? null : args.clone();

        return clone;
    }
}
