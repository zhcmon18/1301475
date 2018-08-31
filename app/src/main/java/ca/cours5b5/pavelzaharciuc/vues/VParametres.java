package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.global.GConstantes;

public class VParametres extends ConstraintLayout
{
    private Spinner spinner_height;
    private Spinner spinner_width;
    private Spinner spinner_toWin;

    public VParametres(Context context)
    {
        super(context);
    }

    public VParametres(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public VParametres(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        spinner_height = this.findViewById(R.id.spinner_height);
        spinner_width = this.findViewById(R.id.spinner_width);
        spinner_toWin = this.findViewById(R.id.spinner_toWin);

        ArrayAdapter<Integer> adapter_height = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<Integer> adapter_width = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<Integer> adapter_toWin = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinner_height.setAdapter(adapter_height);
        spinner_width.setAdapter(adapter_width);
        spinner_toWin.setAdapter(adapter_toWin);

        for (int i = GConstantes.HEIGHT_MIN; i <= GConstantes.HEIGHT_MAX; i++)
        {
            adapter_height.add(i);
        }

        for (int i = GConstantes.WIDTH_MIN; i <= GConstantes.WIDTH_MAX; i++)
        {
            adapter_width.add(i);
        }

        for (int i = GConstantes.TO_WIN_MIN; i <= GConstantes.TO_WIN_MAX; i++)
        {
            adapter_toWin.add(i);
        }

        spinner_height.setSelection(GConstantes.HEIGHT_DEFAULT_POS);
        spinner_width.setSelection(GConstantes.WIDTH_DEFAULT_POS);
        spinner_toWin.setSelection(GConstantes.TO_WIN_DEFAULT_POS);
    }
}
