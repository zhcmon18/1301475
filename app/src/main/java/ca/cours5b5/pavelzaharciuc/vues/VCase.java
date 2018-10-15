package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import ca.cours5b5.pavelzaharciuc.global.GCouleur;

public class VCase extends AppCompatButton {

    public VCase(Context context) {
        super(context);
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int rangee;
    private int colonne;

    public VCase (Context context, int colonne, int rangee) {
        super(context);

        this.rangee = rangee;
        this.colonne = colonne;
        setText("" + rangee + "," + colonne);
    }

    public void afficherJeton(GCouleur jeton) {
        if(jeton.equals(GCouleur.ROUGE)) {
            setBackgroundColor(Color.RED);
        } else {
            setBackgroundColor(Color.YELLOW);
        }
    }
}
