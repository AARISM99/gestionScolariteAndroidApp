package com.example.gestionscolarite.EvaluationCRUD;

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

import com.example.gestionscolarite.Menu;
import com.example.gestionscolarite.ModuleCRUD.AddModule;
import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;
import com.example.gestionscolarite.model.Evaluation;
import com.example.gestionscolarite.model.Filiere;
import com.example.gestionscolarite.model.Module;
import com.example.gestionscolarite.model.Niveau;

import java.util.ArrayList;
import java.util.List;

public class AddEvaluation extends AppCompatActivity {

    EditText note_evaluation, date_evaluation;
    Button addBtn;
    DBHelper DBH = new DBHelper(AddEvaluation.this);
    Spinner spinnerEtudiant;
    Spinner spinnerModule;
    List<String> list_etudiants = new ArrayList<String>();
    List<String> list_etudiants_ids = new ArrayList<String>();
    List<String> list_modules = new ArrayList<String>();
    List<String> list_modules_ids = new ArrayList<String>();
    String etudiant_selected_id;
    String module_selected_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluation);

        Etudiant e = new Etudiant();
        Module m = new Module();

        Cursor ce = DBH.getAllEtudiants();
        Cursor cm = DBH.getAllModules();
        Cursor cf = DBH.getAllFilieres();

        if (ce.moveToFirst()) {
            do {
                System.out.println("id etudiant === " + ce.getString(1));
                list_etudiants.add(ce.getString(3) + " " + ce.getString(4));
                list_etudiants_ids.add(ce.getString(1));

            } while (ce.moveToNext());
        }
        if (cm.moveToFirst()) {
            do {
                System.out.println("id module === " + cm.getString(1));
                list_modules.add(cm.getString(3));
                list_modules_ids.add(cm.getString(1));

            } while (cm.moveToNext());
        }

        ArrayAdapter<String> adapter_etudiants = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_etudiants);
        ArrayAdapter<String> adapter_modules = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_modules);


        spinnerEtudiant = findViewById(R.id.etudiant_evaluation_select);
        // setContentView(R.layout.activity_add_filiere);

        spinnerModule = findViewById(R.id.module_evaluation_select);

        spinnerEtudiant.setAdapter(adapter_etudiants);
        spinnerModule.setAdapter(adapter_modules);


        spinnerEtudiant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddEvaluation.this,list_etudiants_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
                etudiant_selected_id = list_etudiants_ids.get(position);
                e.setId(Integer.parseInt(etudiant_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerModule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddEvaluation.this,list_modules_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
                module_selected_id = list_modules_ids.get(position);
                m.setId(Integer.parseInt(module_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        note_evaluation = (EditText) findViewById(R.id.note_evaluation_input);
        date_evaluation = (EditText) findViewById(R.id.date_evaluation_input);

        addBtn = (Button) findViewById(R.id.addEvaluationBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Evaluation E = new Evaluation(
                        e,
                        m,
                        Float.valueOf(note_evaluation.getText().toString()),
                        date_evaluation.getText().toString());

                DBH.insertEvaluation(E);
                Intent i = new Intent(AddEvaluation.this, ListEvaluation.class);
                startActivity(i);

            };
        });
    }
}