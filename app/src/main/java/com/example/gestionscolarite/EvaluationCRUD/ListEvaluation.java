package com.example.gestionscolarite.EvaluationCRUD;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.gestionscolarite.ModuleCRUD.AddModule;
import com.example.gestionscolarite.ModuleCRUD.DetailsModule;
import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;
import com.example.gestionscolarite.model.Evaluation;
import com.example.gestionscolarite.model.Module;

import java.util.ArrayList;

class AdaptedEvaluation{

    public int id;
    public String etudiant;
    public String module;
    public float note;
    public String date_evaluation;


    public AdaptedEvaluation() {
    }

    public AdaptedEvaluation(int id, String etudiant, String module, float note, String date_evaluation) {
        this.id = id;
        this.etudiant = etudiant;
        this.module = module;
        this.note = note;
        this.date_evaluation = date_evaluation;
    }
}

class EvaluationAdapter extends ArrayAdapter<AdaptedEvaluation> {
    public EvaluationAdapter(Context context, ArrayList<AdaptedEvaluation> evaluations) {
        super(context, 0, evaluations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AdaptedEvaluation evaluation = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.evaluation, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.id_evaluation);
        TextView etudiant = (TextView) convertView.findViewById(R.id.etudiant_evaluation_text);
        TextView module = (TextView) convertView.findViewById(R.id.module_evaluation_text);
        TextView note = (TextView) convertView.findViewById(R.id.note_evaluation_text);
        TextView date_evaluation = (TextView) convertView.findViewById(R.id.date_evaluation_text);

        // Populate the data into the template view using the data object
        id.setText(Integer.toString(evaluation.id));
        etudiant.setText(evaluation.etudiant);
        module.setText(evaluation.module);
        note.setText(String.valueOf(evaluation.note));
        date_evaluation.setText(evaluation.date_evaluation);

        // Return the completed view to render on screen
        return convertView;
    }
}


public class ListEvaluation extends AppCompatActivity {
    DBHelper DBH = new DBHelper(ListEvaluation.this);
    Button newEvaluationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_evaluation);
        newEvaluationBtn = (Button) findViewById(R.id.newEvaluationBtn);

        newEvaluationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AddEvaluation.class);
                startActivity(i);
            }

            ;
        });


        Cursor c = DBH.getAllEvaluations();

        int length = c.getCount();

        // Construct the data source
        ArrayList<AdaptedEvaluation> adaptedArray = new ArrayList<AdaptedEvaluation>();
        ArrayList<String> array_etudiants_id = new ArrayList<String>();
        ArrayList<String> array_modules_id = new ArrayList<String>();


        if (c.moveToFirst()) {
            for (int i = 0; i < length; i++) {
                adaptedArray.add(new AdaptedEvaluation(c.getInt(1),
                        DBH.getSelectedEtudiant(c.getInt(2)).getNom_etudiant() + " " + DBH.getSelectedEtudiant(c.getInt(2)).getPrenom_etudiant(),
                        DBH.getSelectedModule(c.getInt(3)).getTitre_module(),
                        c.getFloat(4),
                        c.getString(5)));
                array_etudiants_id.add(c.getString(2));
                array_modules_id.add(c.getString(3));
                c.moveToNext();
            }
        }

        // Create the adapter to convert the array to views
        EvaluationAdapter adapter = new EvaluationAdapter(ListEvaluation.this, adaptedArray);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_evaluation);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = view.findViewById(R.id.id_evaluation);
                Intent in = new Intent(ListEvaluation.this, DetailsEvaluation.class);
                in.putExtra("id", tv.getText().toString());
                in.putExtra("id_etudiant", array_etudiants_id.get(i));
                in.putExtra("id_module", array_modules_id.get(i));
                startActivity(in);
            }
        });

    }
}