package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.Action;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.global.GCommande;

public class VMenuPrincipal extends Vue {

    private Button buttonParam;

    static{ Log.d("Atelier04", VMenuPrincipal.class.getSimpleName() + "::static"); }

    public VMenuPrincipal(Context context) { super(context); }

    public VMenuPrincipal(Context context, AttributeSet attrs) { super(context, attrs); }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        buttonParam = this.findViewById(R.id.button_param);


        buttonParam.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMET);
                action.executerDesQuePossible();
            }
        });
    }
}
