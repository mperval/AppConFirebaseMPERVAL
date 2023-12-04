package ies.carrillo.android.appconfirebasemperval.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import ies.carrillo.android.appconfirebasemperval.R;
import ies.carrillo.android.appconfirebasemperval.models.Superhero;

public class SuperheroAdapter extends ArrayAdapter<Superhero> {
    private Context context;
    private ArrayList<Superhero> superhero;
    public SuperheroAdapter(Context context, ArrayList<Superhero> superhero) {

        super(context, 0, superhero);
        this.context = context;
        this.superhero = superhero;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Superhero superhero = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_superhero, parent, false);
        }
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPowers = convertView.findViewById(R.id.tvPowers);
        Switch swActive = convertView.findViewById(R.id.swActive);

        Log.i("superheroObjetoEnAdaptador", superhero.toString());

        if (superhero != null) {

            tvId.setText(String.valueOf(superhero.getId()));
            Log.i("idAdapter", String.valueOf(superhero.getId()));
            Log.i("text", tvId.getText().toString());

            tvName.setText(superhero.getName());
            Log.i("nameAdapter", superhero.getName());
            Log.i("text", tvName.getText().toString());

            StringBuilder powersText = new StringBuilder();
            ArrayList<String> powers = superhero.getPowers();
            for (String power : powers) {
                powersText.append(power).append("\n"); // Agrego cada poder en una línea nueva
                Log.i("powerAdapter", power);
                tvPowers.setText(powersText);
            }
            Log.i("text", tvPowers.getText().toString());
            swActive.setChecked(superhero.isActive());

            // Manejo eventos de cambio para el Switch si es necesario
            swActive.setOnCheckedChangeListener((buttonView, isChecked) -> {
                // Actualizo el estado 'active' del superheroe cuando cambie el Switch
                superhero.setActive(isChecked);
            });
        }

        return convertView;
    }
}
