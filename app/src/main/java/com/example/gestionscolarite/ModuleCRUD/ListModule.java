package com.example.gestionscolarite.ModuleCRUD;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;

import java.util.ArrayList;

class AdaptedModule{

    public int id;
    public String code_module;
    public String nom_module;
    public String niveau;
    public String filiere;


    public AdaptedModule() {
    }

    public AdaptedModule(int id, String code_module, String nom_module, String niveau,String filiere) {
        this.id = id;
        this.code_module = code_module;
        this.nom_module = nom_module;
        this.niveau = niveau;
        this.filiere = filiere;
    }
}

class ModuleAdapter extends ArrayAdapter<AdaptedModule> {
    public ModuleAdapter(Context context, ArrayList<AdaptedModule> modules) {
        super(context, 0, modules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AdaptedModule module = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.module, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.id_module);
        TextView code = (TextView) convertView.findViewById(R.id.code_module_text);
        TextView nom = (TextView) convertView.findViewById(R.id.nom_module_text);
        TextView niveau = (TextView) convertView.findViewById(R.id.niveau_module_text);
        TextView filiere = (TextView) convertView.findViewById(R.id.filiere_module_text);

        // Populate the data into the template view using the data object
        id.setText(Integer.toString(module.id));
        code.setText(module.code_module);
        nom.setText(module.nom_module);
        niveau.setText(module.niveau);
        filiere.setText(module.filiere);

        // Return the completed view to render on screen
        return convertView;
    }
}


public class ListModule extends AppCompatActivity {
    DBHelper DBH = new DBHelper(com.example.gestionscolarite.ModuleCRUD.ListModule.this);
    Button newModuleBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_module);
        newModuleBtn = (Button) findViewById(R.id.newModuleBtn);

        newModuleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AddModule.class);
                startActivity(i);
            };
        });


        Cursor c = DBH.getAllModules();

        int length = c.getCount();


        // Construct the data source
        ArrayList<AdaptedModule> adaptedArray = new ArrayList<AdaptedModule>();
        ArrayList<String> array_niveaux_id = new ArrayList<String>();
        ArrayList<String> array_filieres_id = new ArrayList<String>();


        if (c.moveToFirst()) {
            for (int i = 0; i < length; i++) {
                adaptedArray.add(new AdaptedModule(c.getInt(1),
                        c.getString(2),
                        c.getString(3),
                        DBH.getSelectedNiveau(c.getInt(4)).getTitre_niveau(),
                        DBH.getSelectedFiliere(c.getInt(5)).getNom_filiere()
                        )
                );
                array_niveaux_id.add(c.getString(4));
                array_filieres_id.add(c.getString(5));
                c.moveToNext();
            }
        }

        // Create the adapter to convert the array to views
        ModuleAdapter adapter = new ModuleAdapter(ListModule.this, adaptedArray);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_module);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = view.findViewById(R.id.id_module);
                Intent in = new Intent(ListModule.this, DetailsModule.class);
                in.putExtra("id",tv.getText().toString());
                in.putExtra("id_niveau", array_niveaux_id.get(i));
                in.putExtra("id_filiere", array_filieres_id.get(i));
                startActivity(in);
            }
        });

    }

}
