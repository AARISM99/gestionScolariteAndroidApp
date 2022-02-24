package com.example.gestionscolarite.EtudiantCRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;

public class DetailsEtudiant extends AppCompatActivity {

    EditText cne,nom,prenom,date_naissance;
    Button editBtn, deleteBtn;
    String id;
    DBHelper DBH = new DBHelper(DetailsEtudiant.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_etudiant);

        cne = findViewById(R.id.details_cne_input);
        nom = findViewById(R.id.details_nom_input);
        prenom = findViewById(R.id.details_prenom_input);
        date_naissance = findViewById(R.id.details_date_naissance_input);

        editBtn = findViewById(R.id.modifier);
        deleteBtn = findViewById(R.id.supp);

        id = getIntent().getStringExtra("id");

        Etudiant E = DBH.getSelectedEtudiant(Integer.parseInt(id));

        cne.setText(E.getCode_etudiant());
        nom.setText(E.getNom_etudiant());
        prenom.setText(E.getPrenom_etudiant());
        date_naissance.setText(E.getDate_naissance());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Etudiant E = new Etudiant(
                        Integer.parseInt(id),
                        cne.getText().toString(),
                        nom.getText().toString(),
                        prenom.getText().toString(),
                        date_naissance.getText().toString());

                DBH.updateEtudiant(E);
                Intent in = new Intent(DetailsEtudiant.this, ListEtudiant.class);
                startActivity(in);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBH.deleteEtudiant(Integer.parseInt(id));

                Intent in = new Intent(DetailsEtudiant.this, ListEtudiant.class);
                startActivity(in);
            }
        });

    }
}