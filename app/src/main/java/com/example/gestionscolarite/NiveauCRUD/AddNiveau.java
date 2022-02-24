package com.example.gestionscolarite.NiveauCRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionscolarite.R;
import com.example.gestionscolarite.model.DBHelper;
import com.example.gestionscolarite.model.Niveau;

public class AddNiveau extends AppCompatActivity {

    EditText code_niveau, titre_niveau;
    Button addBtn;
    DBHelper DBH = new DBHelper(com.example.gestionscolarite.NiveauCRUD.AddNiveau.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_niveau);

        code_niveau = (EditText) findViewById(R.id.code_niveau_input);
        titre_niveau = (EditText) findViewById(R.id.titre_niveau_input);

        addBtn = (Button) findViewById(R.id.addNiveauBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Niveau n = new Niveau(
                    code_niveau.getText().toString(),
                    titre_niveau.getText().toString());

                DBH.insertNiveau(n);
                Intent i = new Intent(com.example.gestionscolarite.NiveauCRUD.AddNiveau.this, ListNiveau.class);
                startActivity(i);

            };
        });
    }
}
