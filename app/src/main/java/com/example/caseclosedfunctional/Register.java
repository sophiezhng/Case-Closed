package com.example.caseclosedfunctional;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText fullName, email, password;
    private FirebaseAuth fAuth;
    Button regButton, goToLogin;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.nameInputREG);
        email = findViewById(R.id.emailInputREG);
        password = findViewById(R.id.passInputREG);

        goToLogin = findViewById(R.id.goToLogin);
        regButton = findViewById(R.id.regButton);

        fAuth = FirebaseAuth.getInstance();

        goToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });
    }
}