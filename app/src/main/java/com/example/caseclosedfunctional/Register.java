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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText fullName, email, password;
    FirebaseAuth fAuth;
    Button regButton, goToLogin;
    ProgressBar progressBar;
    User user;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        goToLogin = findViewById(R.id.goToLogin);
        fullName = findViewById(R.id.nameInputREG);
        email = findViewById(R.id.emailInputREG);
        password = findViewById(R.id.passInputREG);
        progressBar = findViewById(R.id.progressBar);
        regButton = findViewById(R.id.regButton);

        fAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        goToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        //if (fAuth.getCurrentUser() != null) {
       //     startActivity(new Intent(Register.this, MainActivity.class));
       //     finish();
       // }

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = email.getText().toString().trim();
                String userPass = password.getText().toString().trim();
                final String name = fullName.getText().toString().trim();

                if (name.isEmpty()) {
                    fullName.setError("You forgot to enter your name!");
                }
                if (userEmail.isEmpty()) {
                    email.setError("You forgot to enter your email!");
                }
                if (userPass.isEmpty()) {
                    password.setError("You forgot to set a password!");
                }
                if (userPass.length() > 15) {
                    password.setError("Please keep passwords under 15 characters.");
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Account registration was successful!", Toast.LENGTH_SHORT).show();
                            user = new User(name, userEmail);
                            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //user.Uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        Toast.makeText(Register.this, "Database Updated!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Register.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT);
                                    }
                                }
                            });
                            Settings.launch(Register.this, user);
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Error: " + task.getException().getMessage() + " Please retry registration!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}