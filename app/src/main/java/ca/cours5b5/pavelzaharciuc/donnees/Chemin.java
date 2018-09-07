package ca.cours5b5.pavelzaharciuc.donnees;

import java.util.ArrayList;
import java.util.Collections;

import ca.cours5b5.pavelzaharciuc.exceptions.ErreurChemin;
import ca.cours5b5.pavelzaharciuc.global.GConstantes;

public class Chemin extends ArrayList<String> {

    public Chemin(String... segments) {

        Collections.addAll(this, segments);
    }


    @Override
    public String toString() throws ErreurChemin {

        String chemin;

        if(this.size() == 1) {

            chemin = this.get(0);

        } else if (this.size() > 1) {

            StringBuilder builder;

            chemin = this.get(0);

            for(String unSegment : this.subList(1, size() - 1)) {
                builder = new StringBuilder();
                builder.append(chemin);
                builder.append(GConstantes.SEPARATEUR);
                builder.append(unSegment);
                chemin = builder.toString();
            }

        } else {

            throw new ErreurChemin("le chemin est vide.");
        }

        return chemin;
    }
}
