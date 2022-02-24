package com.example.gestionscolarite.FiliereCRUD;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Filiere;
import com.example.gestionscolarite.model.Niveau;

import java.util.ArrayList;
import java.util.List;

class AdaptedFiliere{

    public int id;
    public String code_filiere;
    public String nom_filiere;
    public String niveau;

    public AdaptedFiliere() {
    }

    public AdaptedFiliere(int id, String code_filiere, String nom_filiere, String niveau) {
        this.id = id;
        this.code_filiere = code_filiere;
        this.nom_filiere = nom_filiere;
        this.niveau = niveau;
    }
}

class FiliereAdapter extends ArrayAdapter<AdaptedFiliere> {
    public FiliereAdapter(Context context, ArrayList<AdaptedFiliere> filieres) {
        super(context, 0, filieres);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AdaptedFiliere filiere = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.filiere, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.id_filiere);
        TextView code = (TextView) convertView.findViewById(R.id.code_filiere_text);
        TextView nom = (TextView) convertView.findViewById(R.id.nom_filiere_text);
        TextView niveau = (TextView) convertView.findViewById(R.id.niveau_filiere_text);

        // Populate the data into the template view using the data object
        id.setText(Integer.toString(filiere.id));
        code.setText(filiere.code_filiere);
        nom.setText(filiere.nom_filiere);
        niveau.setText(filiere.niveau);

        // Return the completed view to render on screen
        return convertView;
    }
}


public class ListFiliere extends AppCompatActivity {
    ListView ls;
    DBHelper DBH = new DBHelper(ListFiliere.this);
    Button newFiliereBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_filiere);
        newFiliereBtn = (Button) findViewById(R.id.newFiliereBtn);

        newFiliereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AddFiliere.class);
                startActivity(i);
            };
        });

        Cursor c = DBH.getAllFilieres();

        int length = c.getCount();


        // Construct the data source
        ArrayList<AdaptedFiliere> adaptedArray = new ArrayList<AdaptedFiliere>();
        ArrayList<String> array_niveau_id = new ArrayList<String>();


        if (c.moveToFirst()) {
            for (int i = 0; i < length; i++) {
                adaptedArray.add(new AdaptedFiliere(c.getInt(1),c.getString(2),c.getString(3),DBH.getSelectedNiveau(c.getInt(4)).getTitre_niveau()));
                array_niveau_id.add(c.getString(4));
                c.moveToNext();
            }
        }

        // Create the adapter to convert the array to views
        FiliereAdapter adapter = new FiliereAdapter(ListFiliere.this, adaptedArray);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_filiere);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = view.findViewById(R.id.id_filiere);
                Intent in = new Intent(ListFiliere.this, DetailsFiliere.class);
                in.putExtra("id",tv.getText().toString());
                in.putExtra("id_niveau", array_niveau_id.get(i));
                startActivity(in);
            }
        });

    }

}
