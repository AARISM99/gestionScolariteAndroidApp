package com.example.gestionscolarite.InscriptionCRUD;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;
import com.example.gestionscolarite.model.Filiere;
import com.example.gestionscolarite.model.Inscription;
import com.example.gestionscolarite.model.Niveau;

import java.util.ArrayList;
import java.util.List;

public class DetailsInscription extends AppCompatActivity {

    EditText date_inscription;
    Spinner spinnerNiveau, spinnerFiliere, spinnerEtudiant;
    List<String> list_niveaux = new ArrayList<String>();
    List<String> list_niveaux_ids = new ArrayList<String>();
    List<String> list_filieres = new ArrayList<String>();
    List<String> list_filieres_ids = new ArrayList<String>();
    List<String> list_etudiants = new ArrayList<String>();
    List<String> list_etudiants_ids = new ArrayList<String>();
    DBHelper DBH = new DBHelper(DetailsInscription.this);
    String niveau_selected_id, filiere_selected_id, etudiant_selected_id;
    Button editBtn, deleteBtn;
    String id;
    String id_niveau,id_filiere,id_etudiant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_inscription);

        editBtn = findViewById(R.id.modifier);
        deleteBtn = findViewById(R.id.supp);

        date_inscription = (EditText) findViewById(R.id.details_date_inscription_input);

        id = getIntent().getStringExtra("id");
        id_niveau = getIntent().getStringExtra("id_niveau");
        id_filiere = getIntent().getStringExtra("id_filiere");
        id_etudiant = getIntent().getStringExtra("id_etudiant");

        Inscription I = DBH.getSelectedInscription(Integer.parseInt(id));
        date_inscription.setText(I.getDate());


        Niveau n = new Niveau();
        Filiere f = new Filiere();
        Etudiant e = new Etudiant();

        Cursor cn = DBH.getAllNiveaux();
        Cursor cf = DBH.getAllFilieres();
        Cursor ce = DBH.getAllEtudiants();

        if (cn.moveToFirst()) {
            do {
                System.out.println("id === " + cn.getString(1));
                list_niveaux.add(cn.getString(3));
                list_niveaux_ids.add(cn.getString(1));

            } while (cn.moveToNext());
        }

        if (cf.moveToFirst()) {
            do {
                System.out.println("id === " + cf.getString(1));
                list_filieres.add(cf.getString(3));
                list_filieres_ids.add(cf.getString(1));

            } while (cf.moveToNext());
        }

        if (ce.moveToFirst()) {
            do {
                System.out.println("id === " + ce.getString(1));
                list_etudiants.add(ce.getString(3) + " " + ce.getString(4));
                list_etudiants_ids.add(ce.getString(1));

            } while (ce.moveToNext());
        }


        ArrayAdapter<String> adapter_niveaux = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_niveaux);
        ArrayAdapter<String> adapter_filieres = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_filieres);
        ArrayAdapter<String> adapter_etudiants = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_etudiants);


        spinnerNiveau = findViewById(R.id.details_niveau_inscription_select);
        spinnerFiliere = findViewById(R.id.details_filiere_inscription_select);
        spinnerEtudiant = findViewById(R.id.details_etudiant_inscription_select);
        // setContentView(R.layout.activity_add_filiere);



        spinnerNiveau.setAdapter(adapter_niveaux);
        spinnerFiliere.setAdapter(adapter_filieres);
        spinnerEtudiant.setAdapter(adapter_etudiants);

        spinnerNiveau.setSelection(list_niveaux.indexOf(id_niveau));
        spinnerFiliere.setSelection(list_filieres.indexOf(id_filiere));
        spinnerEtudiant.setSelection(list_etudiants.indexOf(id_etudiant));


        spinnerNiveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DetailsInscription.this, list_niveaux_ids.get(position) + " Selected", Toast.LENGTH_LONG).show();
                niveau_selected_id = list_niveaux_ids.get(position);
                n.setId(Integer.parseInt(niveau_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerFiliere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DetailsInscription.this, list_filieres_ids.get(position) + " Selected", Toast.LENGTH_LONG).show();
                filiere_selected_id = list_filieres_ids.get(position);
                f.setId(Integer.parseInt(filiere_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEtudiant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DetailsInscription.this, list_etudiants_ids.get(position) + " Selected", Toast.LENGTH_LONG).show();
                etudiant_selected_id = list_etudiants_ids.get(position);
                e.setId(Integer.parseInt(etudiant_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inscription I = new Inscription(
                    Integer.parseInt(id),
                    n,
                    f,
                    e,
                    date_inscription.getText().toString()
                );

                DBH.updateInscription(I);
                Intent in = new Intent(DetailsInscription.this, ListInscription.class);
                startActivity(in);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBH.deleteInscription(Integer.parseInt(id));

                Intent in = new Intent(DetailsInscription.this, ListInscription.class);
                startActivity(in);
            }
        });
    }

}