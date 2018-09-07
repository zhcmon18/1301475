package ca.cours5b5.pavelzaharciuc.donnees;

public class Sauvegarde {

    public static Chemin getCheminDeSauvegarde(Class<? extends Sauvegardable> classeASauvegarder) {
        return new Chemin(classeASauvegarder.getSimpleName());
    }
}
