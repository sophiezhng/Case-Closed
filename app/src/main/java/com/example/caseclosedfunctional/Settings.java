package com.example.caseclosedfunctional;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity {
    Button enter;
    CheckBox lung, diabetes, neurologic, heart, dialysis, liver, preg, obese;
    RadioButton yesNurse, noNurse, yesPlan, noPlan;
    RadioGroup nurse, plan;
    User user;
    FirebaseAuth fAuth;
    FirebaseDatabase fData;
    DatabaseReference fRef, databaseReference;
    NumberPicker numberPicker;

    public static void launch(Context context, User user){
        Intent i = new Intent(context, Settings.class);
        i.putExtras(user.getUserBundle());
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        enter                = findViewById(R.id.button);
        lung                 = findViewById(R.id.checkbox_lung);
        diabetes             = findViewById(R.id.checkbox_diabetes);
        neurologic           = findViewById(R.id.checkbox_neurologic);
        heart                = findViewById(R.id.checkbox_heart);
        dialysis             = findViewById(R.id.checkbox_dialysis);
        liver                = findViewById(R.id.checkbox_liver);
        preg                 = findViewById(R.id.checkbox_preg);
        obese                = findViewById(R.id.checkbox_obese);
        yesNurse             = findViewById(R.id.yes_nurse);
        noNurse              = findViewById(R.id.no_nurse);
        yesPlan              = findViewById(R.id.yes_plan);
        noPlan               = findViewById(R.id.no_plan);
        nurse                = findViewById(R.id.group_nurse);
        plan                 = findViewById(R.id.group_plan);
        numberPicker         = findViewById(R.id.numberPicker);
        fAuth                = FirebaseAuth.getInstance();
        fData                = FirebaseDatabase.getInstance();
        fRef                 = fData.getReference("Users");
        databaseReference    = fData.getReference("objRef");


        Bundle b = getIntent().getExtras();
        user = User.userFromBundle(b);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(120);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RADIO BUTTON HANDLING
                int nurseRadio = nurse.getCheckedRadioButtonId();
                int planRadio = plan.getCheckedRadioButtonId();
                if(nurseRadio == -1){
                    noNurse.setError("Please complete this field");
                    return;
                }
                if(planRadio == -1){
                    noPlan.setError("Please complete this field");
                    return;
                }


                System.out.println(nurseRadio + " " + planRadio);

                if(nurseRadio == 2131165475){
                    user.setNH(true);
                }
                if(planRadio == 2131165476){
                    user.plan = true;
                }

                //CHECK ALL THAT APPLY HANDLING
                if(lung.isChecked()){
                    user.condition++;
                }
                if(diabetes.isChecked()){
                    user.condition++;
                }
                if(neurologic.isChecked()){
                    user.condition++;
                }
                if(heart.isChecked()){
                    user.condition++;
                }
                if(liver.isChecked()){
                    user.condition++;
                }
                if(preg.isChecked()){
                    user.condition++;
                }
                if(obese.isChecked()){
                    user.condition++;
                }
                //code for vars update
                user.setAge(numberPicker.getValue());

                fRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //user.Uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            Toast.makeText(Settings.this, "Database Updated!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Settings.this, MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(Settings.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });
    }
}

//maybe hold off on sending information to the database until settings?