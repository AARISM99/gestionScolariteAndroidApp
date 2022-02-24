package com.example.gestionscolarite.EtudiantCRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Etudiant;

public class ListEtudiant extends AppCompatActivity {

    ListView ls;
    DBHelper DBH = new DBHelper(ListEtudiant.this);
    Button newEtudiantBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);
        newEtudiantBtn = (Button) findViewById(R.id.newEtudiantBtn);

        newEtudiantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),AddEtudiant.class);
                startActivity(i);
            };
        });

        ls = findViewById(R.id.list_etudiant);

        Cursor c = DBH.getAllEtudiants();

        System.out.println("Liste des etudiants");
        System.out.println(c.getColumnName(0) + " " + c.getColumnName(1));

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
            ListEtudiant.this,
            R.layout.etudiant,
            c,
            new String[]{c.getColumnName(1),c.getColumnName(2),c.getColumnName(3),c.getColumnName(4),c.getColumnName(5)},
            new int[]{R.id.id,R.id.cne_text, R.id.nom_text,R.id.prenom_text, R.id.date_naissance_text},
            1
        );

        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = view.findViewById(R.id.id);
                Intent in = new Intent(ListEtudiant.this,DetailsEtudiant.class);
                in.putExtra("id",tv.getText().toString());
                startActivity(in);
            }
        });

    }
}