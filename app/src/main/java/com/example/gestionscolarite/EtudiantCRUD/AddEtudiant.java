package com.example.gestionscolarite.EtudiantCRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionscolarite.Menu;
import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;

public class AddEtudiant extends AppCompatActivity {

    EditText code_etudiant, nom_etudiant, prenom_etudiant, date_naissance;
    Button addBtn;
    DBHelper DBH = new DBHelper(AddEtudiant.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        code_etudiant = (EditText) findViewById(R.id.code_etudiant_input);
        nom_etudiant = (EditText) findViewById(R.id.nom_etudiant_input);
        prenom_etudiant = (EditText) findViewById(R.id.prenom_etudiant_input);
        date_naissance = (EditText) findViewById(R.id.date_naissace_input);
        addBtn = (Button) findViewById(R.id.addEtudiantBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Etudiant e = new Etudiant(
                        code_etudiant.getText().toString(),
                        nom_etudiant.getText().toString(),
                        prenom_etudiant.getText().toString(),
                        date_naissance.getText().toString());

                DBH.insertEtudiant(e);
                Intent i = new Intent(AddEtudiant.this, ListEtudiant.class);
                startActivity(i);

            };
        });
    }
}