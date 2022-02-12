package com.example.gestionscolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gestionscolarite.EtudiantCRUD.AddEtudiant;

public class Menu extends AppCompatActivity {
    Button etudiantBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        etudiantBtn = (Button) findViewById(R.id.etudiantBtn);
        etudiantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //open Menu activity
                    startActivity(new Intent(getApplicationContext(), AddEtudiant.class));

            }
        });
    }
}