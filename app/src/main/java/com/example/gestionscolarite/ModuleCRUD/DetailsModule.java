package com.example.gestionscolarite.ModuleCRUD;

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

import com.example.gestionscolarite.FiliereCRUD.DetailsFiliere;
import com.example.gestionscolarite.FiliereCRUD.ListFiliere;
import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Filiere;
import com.example.gestionscolarite.model.Module;
import com.example.gestionscolarite.model.Niveau;

import java.util.ArrayList;
import java.util.List;

public class DetailsModule extends AppCompatActivity {
    EditText code_module,nom_module;
    Spinner niveau_module;
    Spinner filiere_module;
    Button editBtn, deleteBtn;
    String id;
    String id_niveau;
    String id_filiere;
    DBHelper DBH = new DBHelper(DetailsModule.this);
    Spinner spinnerNiveau;
    Spinner spinnerFiliere;
    List<String> list_niveaux = new ArrayList<String>();
    List<String> list_niveaux_ids = new ArrayList<String>();
    List<String> list_filieres = new ArrayList<String>();
    List<String> list_filieres_ids = new ArrayList<String>();
    String niveau_selected_id;
    String filiere_selected_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_module);

        code_module = findViewById(R.id.details_code_module_input);
        nom_module = findViewById(R.id.details_nom_module_input);
        niveau_module = findViewById(R.id.details_niveau_module_select);
        filiere_module = findViewById(R.id.details_filiere_module_select);

        editBtn = findViewById(R.id.modifier);
        deleteBtn = findViewById(R.id.supp);

        id = getIntent().getStringExtra("id");
        id_niveau = getIntent().getStringExtra("id_niveau");
        id_filiere = getIntent().getStringExtra("id_filiere");

        Module M = DBH.getSelectedModule(Integer.parseInt(id));
        M.getNiveau().setId(Integer.parseInt(id_niveau));
        M.getFiliere().setId(Integer.parseInt(id_filiere));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(M);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        code_module.setText(M.getCode_module());
        nom_module.setText(M.getTitre_module());

        Cursor cn = DBH.getAllNiveaux();
        Cursor cf = DBH.getAllFilieres();

        if (cn.moveToFirst()) {
            do {
                System.out.println("id module === " + cn.getString(1));
                System.out.println("nom module === " + cn.getString(3));
                list_niveaux.add(cn.getString(3));
                list_niveaux_ids.add(cn.getString(1));

            } while (cn.moveToNext());
        }

        if (cf.moveToFirst()) {
            do {
                System.out.println("id module === " + cf.getString(1));
                System.out.println("nom module === " + cf.getString(3));
                list_filieres.add(cf.getString(3));
                list_filieres_ids.add(cf.getString(1));

            } while (cf.moveToNext());
        }


        ArrayAdapter<String> adapter_niveaux = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_niveaux);
        ArrayAdapter<String> adapter_filieres = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_filieres);


        spinnerNiveau = findViewById(R.id.details_niveau_module_select);
        spinnerFiliere = findViewById(R.id.details_filiere_module_select);
        // setContentView(R.layout.activity_add_filiere);

        spinnerNiveau.setAdapter(adapter_niveaux);
        spinnerFiliere.setAdapter(adapter_filieres);

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
            +"\nindex === " + list_niveaux_ids.indexOf(id_niveau)
            +"\nid === " + id_niveau
            +"\nniveau === " + list_niveaux.get(list_niveaux_ids.indexOf(id_niveau))
            +"\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
                +"\nindex === " + list_filieres_ids.indexOf(id_filiere)
                +"\nid === " + id_filiere
                +"\nniveau === " + list_filieres.get(list_filieres_ids.indexOf(id_filiere))
                +"\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        spinnerNiveau.setSelection(list_niveaux_ids.indexOf(id_niveau));
        spinnerFiliere.setSelection(list_filieres_ids.indexOf(id_filiere));

        Niveau n = new Niveau();
        Filiere f = new Filiere();

        spinnerNiveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DetailsModule.this,list_niveaux_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
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
                Toast.makeText(DetailsModule.this,list_filieres_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
                filiere_selected_id = list_filieres_ids.get(position);
                f.setId(Integer.parseInt(filiere_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Module M = new Module(
                        Integer.parseInt(id),
                        code_module.getText().toString(),
                        nom_module.getText().toString(),
                        n,
                        f);

                DBH.updateModule(M);
                Intent in = new Intent(DetailsModule.this, ListModule.class);
                startActivity(in);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBH.deleteModule(Integer.parseInt(id));

                Intent in = new Intent(DetailsModule.this, ListModule.class);
                startActivity(in);
            }
        });
    }
}
