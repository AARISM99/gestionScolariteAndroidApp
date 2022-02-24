package com.example.gestionscolarite.InscriptionCRUD;

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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;

import java.util.ArrayList;

class AdaptedInscription{

    public int id;
    public String niveau;
    public String filiere;
    public String etudiant;
    public String date;

    public AdaptedInscription() {
    }

    public AdaptedInscription(int id, String niveau, String filiere, String etudiant, String date) {
        this.id = id;
        this.niveau = niveau;
        this.filiere = filiere;
        this.etudiant = etudiant;
        this.date = date;
    }
}

class InscriptionAdapter extends ArrayAdapter<AdaptedInscription> {
    public InscriptionAdapter(Context context, ArrayList<AdaptedInscription> inscriptions) {
        super(context, 0, inscriptions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AdaptedInscription inscription = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.inscription, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.id_inscription);
        TextView date = (TextView) convertView.findViewById(R.id.date_inscription_text);
        TextView niveau = (TextView) convertView.findViewById(R.id.niveau_inscription_text);
        TextView filiere = (TextView) convertView.findViewById(R.id.filiere_inscription_text);
        TextView etudiant = (TextView) convertView.findViewById(R.id.etudiant_inscription_text);

        // Populate the data into the template view using the data object
        id.setText(Integer.toString(inscription.id));
        date.setText(inscription.date);
        niveau.setText(inscription.niveau);
        filiere.setText(inscription.filiere);
        etudiant.setText(inscription.etudiant);

        // Return the completed view to render on screen
        return convertView;
    }
}


public class ListInscription extends AppCompatActivity {
    DBHelper DBH = new DBHelper(ListInscription.this);
    Button newInscriptionBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_inscription);
        newInscriptionBtn = (Button) findViewById(R.id.newInscriptionBtn);

        newInscriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AddInscription.class);
                startActivity(i);
            };
        });


        // Construct the data source
        ArrayList<AdaptedInscription> adaptedArray = new ArrayList<AdaptedInscription>();
        ArrayList<String> array_niveaux_id = new ArrayList<String>();
        ArrayList<String> array_filieres_id = new ArrayList<String>();
        ArrayList<String> array_etudiants_id = new ArrayList<String>();

        Cursor c = DBH.getAllInscriptions();

        int length = c.getCount();

        System.out.println("nombre des inscriptions == " + length);

        if (c.moveToFirst()) {
            for (int i = 0; i < length; i++) {
                System.out.println(c.getColumnName(1)
                        + "\n" + c.getColumnName(2)
                        + "\n" + c.getColumnName(3)
                        + "\n" + c.getColumnName(4)
                        + "\n" + c.getColumnName(5));
                adaptedArray.add(new AdaptedInscription(c.getInt(1),
                        DBH.getSelectedNiveau(c.getInt(4)).getTitre_niveau(),
                        DBH.getSelectedFiliere(c.getInt(3)).getNom_filiere(),
                        DBH.getSelectedEtudiant(c.getInt(2)).getNom_etudiant() + " " + DBH.getSelectedEtudiant(c.getInt(2)).getPrenom_etudiant(),
                        c.getString(5)
                ));

                array_niveaux_id.add(c.getString(2));
                array_filieres_id.add(c.getString(3));
                array_etudiants_id.add(c.getString(4));

                c.moveToNext();
            }
        }

        // Create the adapter to convert the array to views
        InscriptionAdapter adapter = new InscriptionAdapter(ListInscription.this, adaptedArray);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_inscription);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = view.findViewById(R.id.id_inscription);
                Intent in = new Intent(ListInscription.this, DetailsInscription.class);
                in.putExtra("id",tv.getText().toString());
                in.putExtra("id_niveau", array_niveaux_id.get(i));
                in.putExtra("id_filiere", array_filieres_id.get(i));
                in.putExtra("id_etudiant", array_etudiants_id.get(i));

                startActivity(in);
            }
        });

    }

}
