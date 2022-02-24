package com.example.gestionscolarite.FiliereCRUD;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.gestionscolarite.EtudiantCRUD.AddEtudiant;
import com.example.gestionscolarite.EtudiantCRUD.ListEtudiant;
import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;
import com.example.gestionscolarite.model.Filiere;
import com.example.gestionscolarite.model.Niveau;
import java.util.ArrayList;
import java.util.List;


public class AddFiliere extends AppCompatActivity {

    EditText code_filiere, nom_filiere;
    Button addBtn;
    Spinner spinnerNiveau;
    List<String> list_niveaux = new ArrayList<String>();
    List<String> list_ids = new ArrayList<String>();
    DBHelper DBH = new DBHelper(AddFiliere.this);
    String niveau_selected_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filiere);

        code_filiere = (EditText) findViewById(R.id.code_filiere_input);
        nom_filiere = (EditText) findViewById(R.id.nom_filiere_input);
        addBtn = (Button) findViewById(R.id.addFiliereBtn);
        Niveau n = new Niveau();


        Cursor c = DBH.getAllNiveaux();
        if (c.moveToFirst()) {
            do {
                System.out.println("id === " + c.getString(1));
                System.out.println("titre === " + c.getString(3));
                list_niveaux.add(c.getString(3));
                list_ids.add(c.getString(1));

            } while (c.moveToNext());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_niveaux);

        spinnerNiveau = findViewById(R.id.niveau_select);
        // setContentView(R.layout.activity_add_filiere);

        spinnerNiveau.setAdapter(adapter);

        spinnerNiveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddFiliere.this,list_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
                niveau_selected_id = list_ids.get(position);
                n.setId(Integer.parseInt(niveau_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filiere f = new Filiere(
                        code_filiere.getText().toString(),
                        nom_filiere.getText().toString(),
                        n);

                DBH.insertFiliere(f);
                Intent i = new Intent(AddFiliere.this, ListFiliere.class);
                startActivity(i);

            };
        });



    }


}