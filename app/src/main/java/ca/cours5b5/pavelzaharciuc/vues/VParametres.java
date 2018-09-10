package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;

public class VParametres extends Vue implements AdapterView.OnItemSelectedListener
{
    static { Log.d("Atelier04", VParametres.class.getSimpleName() + "::static"); }

    private ArrayAdapter<Integer> adapterHauteur;
    private ArrayAdapter<Integer> adapterLargeur;
    private ArrayAdapter<Integer> adapterPourGagner;
    private Spinner spinnerHauteur;
    private Spinner spinnerLargeur;
    private Spinner spinnerPourGagner;

    public VParametres(Context context) { super(context); }

    public VParametres(Context context, AttributeSet attrs) { super(context, attrs); }

    public VParametres(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        initialiserSpinners();

        initialiserAdapteurs();

        setAdapters();

        remplirAdapteurs();

        positionSpinners();
    }

    private void initialiserAdapteurs() {
        adapterHauteur = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);
        adapterLargeur = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);
        adapterPourGagner = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);
    }

    private void initialiserSpinners() {
        spinnerHauteur = findViewById(R.id.spinnerHauteur);
        spinnerLargeur = findViewById(R.id.spinnerLargeur);
        spinnerPourGagner = findViewById(R.id.spinnerPourGagner);

        spinnerHauteur.setOnItemSelectedListener(this);
        spinnerLargeur.setOnItemSelectedListener(this);
        spinnerPourGagner.setOnItemSelectedListener(this);
    }

    private void setAdapters() {
        spinnerHauteur.setAdapter(adapterHauteur);
        spinnerLargeur.setAdapter(adapterLargeur);
        spinnerPourGagner.setAdapter(adapterPourGagner);
    }

    private void remplirAdapteurs() {
        adapterHauteur.addAll(MParametres.instance.getChoixHauteur());
        adapterLargeur.addAll(MParametres.instance.getChoixLargeur());
        adapterPourGagner.addAll(MParametres.instance.getChoixPourGagner());
    }

    private void positionSpinners() {
        spinnerHauteur.setSelection(adapterHauteur.getPosition(MParametres.instance.getHauteur()));
        spinnerLargeur.setSelection(adapterLargeur.getPosition(MParametres.instance.getLargeur()));
        spinnerPourGagner.setSelection(adapterPourGagner.getPosition(MParametres.instance.getPourGagner()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        Integer leChoix = (Integer)parent.getAdapter().getItem(parent.getSelectedItemPosition());

        if(spinner.getId() == R.id.spinnerHauteur) {
            MParametres.instance.setHauteur(leChoix);
        } else if(spinner.getId() == R.id.spinnerLargeur) {
            MParametres.instance.setLargeur(leChoix);
        } else {
            MParametres.instance.setPourGagner(leChoix);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
