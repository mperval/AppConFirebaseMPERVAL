package ies.carrillo.android.appconfirebasemperval.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ies.carrillo.android.appconfirebasemperval.R;
import ies.carrillo.android.appconfirebasemperval.adapters.SuperheroAdapter;
import ies.carrillo.android.appconfirebasemperval.models.Superhero;

public class MainActivity extends AppCompatActivity {
    ListView lvSuperhero;
    SuperheroAdapter daSuper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        ArrayList<Superhero> superheros = new ArrayList<>();
        lvSuperhero = findViewById(R.id.lvSuperhero);

        DatabaseReference dbSuperheros = FirebaseDatabase.getInstance().getReference().child("superheros");

        dbSuperheros.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                superheros.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    long id = snapshot.child("id").getValue(Long.class);
                    String name = snapshot.child("name").getValue(String.class);
                    boolean active = snapshot.child("active").getValue(Boolean.class);

                    ArrayList<String> powers = new ArrayList<>();

                    for (DataSnapshot powerSnapshot : snapshot.child("powers").getChildren()) {
                        String power = powerSnapshot.getValue(String.class);
                        Log.i("power", power);
                        powers.add(power);
                    }

                    Superhero superhero = new Superhero(id, name, powers, active);
                    superheros.add(superhero);
                }

                daSuper = new SuperheroAdapter(getApplicationContext(),  superheros);
                daSuper.notifyDataSetChanged();
                lvSuperhero.setAdapter(daSuper);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w("Firebase", "Error en la lectura en la base de datos.", error.toException());
            }
        });





    }
}