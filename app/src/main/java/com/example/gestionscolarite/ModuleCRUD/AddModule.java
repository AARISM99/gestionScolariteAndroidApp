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

import com.example.gestionscolarite.FiliereCRUD.AddFiliere;
import com.example.gestionscolarite.FiliereCRUD.ListFiliere;
import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Filiere;
import com.example.gestionscolarite.model.Module;
import com.example.gestionscolarite.model.Niveau;

import java.util.ArrayList;
import java.util.List;

public class AddModule extends AppCompatActivity {
    EditText code_module, titre_module;
    Button addBtn;
    Spinner spinnerNiveau;
    Spinner spinnerFiliere;
    List<String> list_niveaux = new ArrayList<String>();
    List<String> list_niveaux_ids = new ArrayList<String>();
    List<String> list_filieres = new ArrayList<String>();
    List<String> list_filieres_ids = new ArrayList<String>();
    DBHelper DBH = new DBHelper(AddModule.this);
    String niveau_selected_id;
    String filiere_selected_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);

        code_module = (EditText) findViewById(R.id.code_module_input);
        titre_module = (EditText) findViewById(R.id.titre_module_input);
        addBtn = (Button) findViewById(R.id.addModuletBtn);
        Niveau n = new Niveau();
        Filiere f = new Filiere();

        Cursor cn = DBH.getAllNiveaux();
        Cursor cf = DBH.getAllFilieres();
        if (cn.moveToFirst()) {
            do {
                System.out.println("id niveau === " + cn.getString(1));
                System.out.println("titre niveau === " + cn.getString(3));
                list_niveaux.add(cn.getString(3));
                list_niveaux_ids.add(cn.getString(1));

            } while (cn.moveToNext());
        }
        if (cf.moveToFirst()) {
            do {
                System.out.println("id filiere === " + cf.getString(1));
                System.out.println("titre filiere === " + cf.getString(3));
                list_filieres.add(cf.getString(3));
                list_filieres_ids.add(cf.getString(1));

            } while (cf.moveToNext());
        }


        ArrayAdapter<String> adapter_niveaux = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_niveaux);
        ArrayAdapter<String> adapter_filieres = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_filieres);


        spinnerNiveau = findViewById(R.id.niveau_module_select);
        // setContentView(R.layout.activity_add_filiere);
        spinnerFiliere = findViewById(R.id.filiere_module_select);

        spinnerNiveau.setAdapter(adapter_niveaux);
        spinnerFiliere.setAdapter(adapter_filieres);

        spinnerNiveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddModule.this,list_niveaux_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
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
                Toast.makeText(AddModule.this,list_filieres_ids.get(position)+ " Selected",Toast.LENGTH_LONG).show();
                filiere_selected_id = list_filieres_ids.get(position);
               f.setId(Integer.parseInt(filiere_selected_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Module m = new Module(
                        code_module.getText().toString(),
                        titre_module.getText().toString(),
                        n,
                        f);

                DBH.insertModule(m);
                Intent i = new Intent(AddModule.this, ListModule.class);
                startActivity(i);

            };
        });



    }
}
