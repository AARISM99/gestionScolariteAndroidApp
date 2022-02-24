package com.example.gestionscolarite.Bultin;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;
import com.example.gestionscolarite.model.Evaluation;
import com.example.gestionscolarite.model.Filiere;
import com.example.gestionscolarite.model.Inscription;
import com.example.gestionscolarite.model.Niveau;

import java.util.ArrayList;

class AdaptedBultin{

    public String module;
    public Float note;

    public AdaptedBultin() {
    }

    public AdaptedBultin(String module, Float note) {
        this.module = module;
        this.note = note;
    }
}

class BultinAdapter extends ArrayAdapter<AdaptedBultin> {
    public BultinAdapter(Context context, ArrayList<AdaptedBultin> bultins) {
        super(context, 0, bultins);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AdaptedBultin bultin = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.module_note, parent, false);
        }
        // Lookup view for data population
        TextView module = (TextView) convertView.findViewById(R.id.bultin_module);
        TextView note = (TextView) convertView.findViewById(R.id.bultin_note);

        // Populate the data into the template view using the data object
        module.setText(bultin.module);
        note.setText(String.valueOf(bultin.note));


        // Return the completed view to render on screen
        return convertView;
    }
}

public class DetailsBultin extends AppCompatActivity {

    EditText code_etudiant;
    TextView nom_etudiant, prenom_etudiant, niveau_etudiant, filiere_etudiant, moyenne_bultin;
    Button voirBtn;
    DBHelper DBH = new DBHelper(DetailsBultin.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bultin);

        code_etudiant = (EditText) findViewById(R.id.bultin_cne_input);
        nom_etudiant = (TextView) findViewById(R.id.bultin_nom_etudiant);
        prenom_etudiant = (TextView) findViewById(R.id.bultin_prenom_etudiant);
        niveau_etudiant = (TextView) findViewById(R.id.bultin_niveau_etudiant);
        filiere_etudiant = (TextView) findViewById(R.id.bultin_filière_etudiant);
        moyenne_bultin = (TextView) findViewById(R.id.bultin_moyenne);


        voirBtn = (Button) findViewById(R.id.voirBtn);

        voirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cne = code_etudiant.getText().toString();
                Etudiant etudiant = DBH.getEtudiantByCNE(cne);
                if(etudiant != null){
                    Inscription inscription = DBH.getInscriptionByEtudiant(etudiant.getId());
                    if(inscription != null){
                        nom_etudiant.setText(etudiant.getNom_etudiant());
                        prenom_etudiant.setText(etudiant.getPrenom_etudiant());
                        Niveau niveau = DBH.getSelectedNiveau(inscription.getNiveau().getId());
                        Filiere filiere = DBH.getSelectedFiliere(inscription.getFiliere().getId());
                        niveau_etudiant.setText(niveau.getTitre_niveau());
                        filiere_etudiant.setText(filiere.getNom_filiere());

                        Cursor ce = DBH.getEvaluationsByEtudiant(etudiant.getId());

                        int length = ce.getCount();


                        System.out.println("length des evaluations == " + length);

                        // Construct the data source
                        ArrayList<AdaptedBultin> adaptedArray = new ArrayList<AdaptedBultin>();

                        Float moyenne = 0.0f;

                        if (ce.moveToFirst()) {
                            for (int i = 0; i < length; i++) {
                                adaptedArray.add(new AdaptedBultin(DBH.getSelectedModule(ce.getInt(3)).getTitre_module(),ce.getFloat(4)));
                                moyenne += ce.getFloat(4);
                                System.out.println("moyenne == " + moyenne);
                                ce.moveToNext();
                            }
                        }

                        System.out.println("moyenne == " + moyenne);

                        moyenne = moyenne / length;

                        moyenne_bultin.setText(String.valueOf(moyenne));

                        // Create the adapter to convert the array to views
                        BultinAdapter adapter = new BultinAdapter(DetailsBultin.this, adaptedArray);
                        // Attach the adapter to a ListView
                        ListView listView = (ListView) findViewById(R.id.list_module_note);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent in = new Intent(DetailsBultin.this, DetailsBultin.class);
                                startActivity(in);
                            }
                        });
                    }
                }

            }
        });
    }
}
