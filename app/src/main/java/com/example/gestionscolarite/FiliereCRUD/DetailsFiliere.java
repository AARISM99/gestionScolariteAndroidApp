package com.example.gestionscolarite.FiliereCRUD;

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
import com.example.gestionscolarite.model.Filiere;
import com.example.gestionscolarite.model.Niveau;

import java.util.ArrayList;
import java.util.List;

public class DetailsFiliere extends AppCompatActivity {
    EditText code_filiere,nom_filiere;
    Spinner niveau_filiere;
    Button editBtn, deleteBtn;
    String id;
    String id_niveau;
    DBHelper DBH = new DBHelper(DetailsFiliere.this);
    Spinner spinnerNiveau;
    List<String> list_niveaux = new ArrayList<String>();
    List<String> list_ids = new ArrayList<String>();
    String niveau_selected_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_filiere);

        code_filiere = findViewById(R.id.details_code_filiere_input);
        nom_filiere = findViewById(R.id.details_nom_filiere_input);
        niveau_filiere = findViewById(R.id.details_niveau_select);

        editBtn = findViewById(R.id.modifier);
        deleteBtn = findViewById(R.id.supp);

        id = getIntent().getStringExtra("id");
        id_niveau = getIntent().getStringExtra("id_niveau");

        Filiere F = DBH.getSelectedFiliere(Integer.parseInt(id));
        F.getNiveau().setId(Integer.parseInt(id_niveau));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(F);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        code_filiere.setText(F.getCode_filiere());
        nom_filiere.setText(F.getNom_filiere());


        Cursor c = DBH.getAllNiveaux();
        if (c.moveToFirst()) {
            do {
                System.out.println("id === " + c.getString(1));
                System.out.println("nom === " + c.getString(3));
                list_niveaux.add(c.getString(3));
                list_ids.add(c.getString(1));


            } while (c.moveToNext());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_niveaux);

        spinnerNiveau = findViewById(R.id.details_niveau_select);
        // setContentView(R.layout.activity_add_filiere);

        spinnerNiveau.setAdapter(adapter);

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("index === " + list_ids.indexOf(id_niveau));
        System.out.println("id === " + id_niveau);
        System.out.println("niveau === " + list_niveaux.get(list_ids.indexOf(id_niveau)));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        spinnerNiveau.setSelection(list_ids.indexOf(id_niveau));


        Niveau n = new Niveau();


        spinnerNiveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DetailsFiliere.this,list_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
                niveau_selected_id = list_ids.get(position);
                n.setId(Integer.parseInt(niveau_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filiere F = new Filiere(
                        Integer.parseInt(id),
                        code_filiere.getText().toString(),
                        nom_filiere.getText().toString(),
                        n);

                DBH.updateFiliere(F);
                Intent in = new Intent(DetailsFiliere.this, ListFiliere.class);
                startActivity(in);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBH.deleteFiliere(Integer.parseInt(id));

                Intent in = new Intent(DetailsFiliere.this, ListFiliere.class);
                startActivity(in);
            }
        });
    }
}
