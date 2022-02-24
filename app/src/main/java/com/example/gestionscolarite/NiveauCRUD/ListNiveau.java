package com.example.gestionscolarite.NiveauCRUD;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionscolarite.EtudiantCRUD.AddEtudiant;
import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;

public class ListNiveau extends AppCompatActivity {

    ListView ls;
    DBHelper DBH = new DBHelper(com.example.gestionscolarite.NiveauCRUD.ListNiveau.this);
    Button newNiveauBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_niveau);

        newNiveauBtn = (Button) findViewById(R.id.newNiveauBtn);

        newNiveauBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AddNiveau.class);
                startActivity(i);
            };
        });

        ls = findViewById(R.id.list_niveau);

        Cursor c = DBH.getAllNiveaux();

        System.out.println("Liste des niveaux");
        System.out.println(c);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                com.example.gestionscolarite.NiveauCRUD.ListNiveau.this,
                R.layout.niveau,
                c,
                new String[]{c.getColumnName(1),c.getColumnName(2),c.getColumnName(3)},
                new int[]{R.id.id,R.id.code_niveau_text,R.id.titre_niveau_text},
                1
        );

        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = view.findViewById(R.id.id);
                Intent in = new Intent(com.example.gestionscolarite.NiveauCRUD.ListNiveau.this, DetailsNiveau.class);
                in.putExtra("id",tv.getText().toString());
                startActivity(in);
            }
        });
    }
}