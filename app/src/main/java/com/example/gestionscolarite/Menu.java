package com.example.gestionscolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gestionscolarite.Bultin.DetailsBultin;
import com.example.gestionscolarite.EtudiantCRUD.AddEtudiant;
import com.example.gestionscolarite.EtudiantCRUD.ListEtudiant;
import com.example.gestionscolarite.EvaluationCRUD.ListEvaluation;
import com.example.gestionscolarite.FiliereCRUD.AddFiliere;
import com.example.gestionscolarite.FiliereCRUD.ListFiliere;
import com.example.gestionscolarite.InscriptionCRUD.AddInscription;
import com.example.gestionscolarite.InscriptionCRUD.ListInscription;
import com.example.gestionscolarite.ModuleCRUD.AddModule;
import com.example.gestionscolarite.ModuleCRUD.ListModule;
import com.example.gestionscolarite.NiveauCRUD.AddNiveau;
import com.example.gestionscolarite.NiveauCRUD.ListNiveau;

public class Menu extends AppCompatActivity {
    Button etudiantBtn;
    Button niveauBtn;
    Button filiereBtn;
    Button moduleBtn;
    Button inscriptionBtn;
    Button evaluationBtn;
    Button bultinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        etudiantBtn = (Button) findViewById(R.id.etudiantBtn);
        niveauBtn = (Button) findViewById(R.id.niveauBtn);
        filiereBtn = (Button) findViewById(R.id.filiereBtn);
        moduleBtn = (Button) findViewById(R.id.moduleBtn);
        inscriptionBtn = (Button) findViewById(R.id.inscriptionBtn);
        evaluationBtn = (Button) findViewById(R.id.evaluationBtn);
        bultinBtn = (Button) findViewById(R.id.bultinBtn);

        etudiantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //open Menu activity
                    startActivity(new Intent(getApplicationContext(), ListEtudiant.class));

            }
        });

        niveauBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //open Menu activity
                startActivity(new Intent(getApplicationContext(), ListNiveau.class));

            }
        });

        filiereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //open Menu activity
                startActivity(new Intent(getApplicationContext(), ListFiliere.class));

            }
        });

        moduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //open Menu activity
                startActivity(new Intent(getApplicationContext(), ListModule.class));

            }
        });

        inscriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //open Menu activity
                startActivity(new Intent(getApplicationContext(), ListInscription.class));

            }
        });

        evaluationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //open Menu activity
                startActivity(new Intent(getApplicationContext(), ListEvaluation.class));

            }
        });

        bultinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //open Menu activity
                startActivity(new Intent(getApplicationContext(), DetailsBultin.class));

            }
        });
    }
}