package com.example.gestionscolarite.EtudiantCRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;

public class ListEtudiant extends AppCompatActivity {

    ListView ls;
    DBHelper DBH = new DBHelper(ListEtudiant.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);

        ls.findViewById(R.id.lst);

        Cursor c = DBH.getAllEtudiants();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                ListEtudiant.this,
                R.layout.etudiant,
                c,new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2),c.getColumnName(3),c.getColumnName(4)},
                new int[]{R.id.id,R.id.cne, R.id.nom,R.id.prenom, R.id.date_naissace},
                1
                );

        ls.setAdapter(adapter);
    }
}