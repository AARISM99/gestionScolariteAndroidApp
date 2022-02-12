package com.example.gestionscolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameText,  passwordText;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login(usernameText.getText().toString(),passwordText.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Welcome back Admin",Toast.LENGTH_LONG).show();

                    //open Menu activity
                    startActivity(new Intent(getApplicationContext(),Menu.class));
                }else
                    Toast.makeText(getApplicationContext(),"Please enter correct Info!",Toast.LENGTH_LONG).show();

            }
        });
    }

    //login

    private boolean login(String username, String password){
        if ((username.isEmpty() || password.isEmpty())) {
            return false;
        }else if(username.equals("admin") && password.equals("password"))
            return true;
        else
            return false;
    }

}