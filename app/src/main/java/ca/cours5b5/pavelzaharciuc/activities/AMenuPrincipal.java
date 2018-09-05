package ca.cours5b5.pavelzaharciuc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.pavelzaharciuc.R;

public class AMenuPrincipal extends Activite {

    static { Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::static"); }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);
        Button buttonParam = this.findViewById(R.id.button_param);


        buttonParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntention = new Intent(AMenuPrincipal.this, AParametres.class);
                AMenuPrincipal.this.startActivity(monIntention);
            }
        });
    }
}
