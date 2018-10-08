package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class VEntete extends AppCompatButton {

    private int colonne;

    public VEntete(Context context) {
        super(context);
    }

    public VEntete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VEntete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VEntete(Context context, int colonne) {
        super(context);
        this.colonne = colonne;

        setText("" + colonne);
    }
}
