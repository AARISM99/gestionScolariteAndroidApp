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

public class DetailsNiveau extends AppCompatActivity {
    EditText code_niveau,titre_niveau;
    Button editBtn, deleteBtn;
    String id;
    DBHelper DBH = new DBHelper(DetailsNiveau.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_niveau);

        code_niveau = findViewById(R.id.details_code_niveau_input);
        titre_niveau = findViewById(R.id.details_titre_niveau_input);

        editBtn = findViewById(R.id.modifier);
        deleteBtn = findViewById(R.id.supp);

        id = getIntent().getStringExtra("id");

        Niveau N = DBH.getSelectedNiveau(Integer.parseInt(id));

        code_niveau.setText(N.getCode_niveau());
        titre_niveau.setText(N.getTitre_niveau());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Niveau N = new Niveau(
                    Integer.parseInt(id),
                    code_niveau.getText().toString(),
                    titre_niveau.getText().toString());
                DBH.updateNiveau(N);
                Intent in = new Intent(DetailsNiveau.this, ListNiveau.class);
                startActivity(in);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBH.deleteNiveau(Integer.parseInt(id));
                Intent in = new Intent(DetailsNiveau.this, ListNiveau.class);
                startActivity(in);
            }
        });

    }
}
