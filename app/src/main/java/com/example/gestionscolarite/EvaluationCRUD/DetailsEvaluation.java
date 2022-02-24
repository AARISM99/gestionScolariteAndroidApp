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

import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;
import com.example.gestionscolarite.model.Evaluation;
import com.example.gestionscolarite.model.Module;

import java.util.ArrayList;
import java.util.List;

public class DetailsEvaluation extends AppCompatActivity {

    EditText note_evaluation,date_evaluation;
    Spinner etudiant_evaluation;
    Spinner module_evaluation;
    Button editBtn, deleteBtn;
    String id;
    String id_etudiant;
    String id_module;
    DBHelper DBH = new DBHelper(DetailsEvaluation.this);
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
        setContentView(R.layout.activity_details_evaluation);

        etudiant_evaluation = findViewById(R.id.details_etudiant_evaluation_select);
        module_evaluation = findViewById(R.id.details_module_evaluation_select);
        note_evaluation = findViewById(R.id.details_note_evaluation_input);
        date_evaluation = findViewById(R.id.details_date_evaluation_input);

        editBtn = findViewById(R.id.modifier);
        deleteBtn = findViewById(R.id.supp);

        id = getIntent().getStringExtra("id");
        id_etudiant = getIntent().getStringExtra("id_etudiant");
        id_module = getIntent().getStringExtra("id_module");

        Evaluation E = DBH.getSelectedEvaluation(Integer.parseInt(id));
        E.getEtudiant().setId(Integer.parseInt(id_etudiant));
        E.getModule().setId(Integer.parseInt(id_module));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(E);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        note_evaluation.setText(String.valueOf(E.getNote()));
        date_evaluation.setText(E.getDate_evaluation());

        Cursor ce = DBH.getAllEtudiants();
        Cursor cm = DBH.getAllModules();

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


        spinnerEtudiant = findViewById(R.id.details_etudiant_evaluation_select);
        spinnerModule = findViewById(R.id.details_module_evaluation_select);
        // setContentView(R.layout.activity_add_filiere);

        spinnerEtudiant.setAdapter(adapter_etudiants);
        spinnerModule.setAdapter(adapter_modules);

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
                +"\nindex === " + list_etudiants.indexOf(id_etudiant)
                +"\nid === " + id_etudiant
                +"\nniveau === " + list_etudiants.get(list_etudiants_ids.indexOf(id_etudiant))
                +"\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
                +"\nindex === " + list_modules_ids.indexOf(id_module)
                +"\nid === " + id_module
                +"\nniveau === " + list_modules.get(list_modules_ids.indexOf(id_module))
                +"\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        spinnerEtudiant.setSelection(list_etudiants_ids.indexOf(id_etudiant));
        spinnerModule.setSelection(list_modules_ids.indexOf(id_module));

        Etudiant e = new Etudiant();
        Module m = new Module();

        spinnerEtudiant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DetailsEvaluation.this,list_etudiants_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
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
                Toast.makeText(DetailsEvaluation.this,list_modules_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
                module_selected_id = list_modules_ids.get(position);
                m.setId(Integer.parseInt(module_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Evaluation E = new Evaluation(
                        Integer.parseInt(id),
                        e,
                        m,
                        Float.valueOf(note_evaluation.getText().toString()),
                        date_evaluation.getText().toString());

                DBH.updateEvaluation(E);
                Intent in = new Intent(DetailsEvaluation.this, ListEvaluation.class);
                startActivity(in);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBH.deleteEvaluation(Integer.parseInt(id));

                Intent in = new Intent(DetailsEvaluation.this, ListEvaluation.class);
                startActivity(in);
            }
        });

    }
}