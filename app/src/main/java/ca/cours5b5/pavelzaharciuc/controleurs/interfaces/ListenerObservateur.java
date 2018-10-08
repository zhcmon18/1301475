package ca.cours5b5.pavelzaharciuc.controleurs.interfaces;

import ca.cours5b5.pavelzaharciuc.modeles.Modele;

public abstract class ListenerObservateur {

    public void reagirNouveauModele(Modele modele) {
        reagirChangementAuModele(modele);
    }

    public abstract void reagirChangementAuModele(Modele modele);
}
