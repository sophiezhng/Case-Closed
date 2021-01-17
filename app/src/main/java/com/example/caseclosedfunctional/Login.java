package com.example.caseclosedfunctional;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button login, goReg;
    EditText email, password;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.passInput);
        progressBar = findViewById(R.id.progressBarLOG);
        login = findViewById(R.id.loginButton);
        goReg = findViewById(R.id.goToReg);

        fAuth = FirebaseAuth.getInstance();

        goReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = email.getText().toString().trim();
                String userPass = password.getText().toString().trim();

                if(userEmail.isEmpty()){
                    email.setError("You forgot to enter your email!");
                }
                if(userPass.isEmpty()){
                    password.setError("You forgot to enter your password!");
                }
                if(userPass.length() > 15){
                    password.setError("Passwords are under 15 characters.");
                }

                fAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Sign-in was successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Error: " + task.getException().getMessage() + " Please retry registration!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}