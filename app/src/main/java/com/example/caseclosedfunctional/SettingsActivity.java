package com.example.caseclosedfunctional;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class SettingsActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button button;
    NumberPicker nPicker;
    RadioGroup groupNurse, groupPlan;
    int numOfHealthConditions = 8;

    // TODO: Upload users personal info onto Firebase Realtime Database solution from these variables
    int age;
    boolean[] healthConditions = new boolean[numOfHealthConditions];
    boolean liveInNursing;
    boolean visitingFacility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        button = findViewById(R.id.button);
        nPicker = findViewById(R.id.numberPicker);
        nPicker.setMaxValue(120);
        nPicker.setMinValue(0);
        nPicker.setValue(30);
        groupNurse = (RadioGroup) findViewById(R.id.group_nurse);
        groupPlan = (RadioGroup) findViewById(R.id.group_plan);
        Arrays.fill(healthConditions, Boolean.FALSE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (groupNurse.getCheckedRadioButtonId() == -1 || groupPlan.getCheckedRadioButtonId() == -1)
                {
                    if (groupNurse.getCheckedRadioButtonId() == -1) {
                        ((RadioButton)groupNurse.getChildAt(groupNurse.getChildCount()-1)).setError("Required Field");
                    }
                    if (groupPlan.getCheckedRadioButtonId() == -1) {
                        ((RadioButton) groupPlan.getChildAt(groupPlan.getChildCount() - 1)).setError("Required Field");
                        // radio buttons not checked: ERROR MESSAGE
                    }
                }
                else
                {
                    ((RadioButton)groupNurse.getChildAt(groupNurse.getChildCount()-1)).setError(null);
                    ((RadioButton) groupPlan.getChildAt(groupPlan.getChildCount() - 1)).setError(null);
                    age = nPicker.getValue();
                    switch(groupNurse.getCheckedRadioButtonId()) {
                        case R.id.yes_nurse:
                            liveInNursing = true;
                            break;
                        case R.id.no_nurse:
                            liveInNursing = false;
                            break;
                    }
                    switch(groupPlan.getCheckedRadioButtonId()) {
                        case R.id.yes_plan:
                            visitingFacility = true;
                            break;
                        case R.id.no_plan:
                            visitingFacility = false;
                            break;
                    }
                    // Android Studio's version of print out, double checking variables r correct
                    Log.i("SettingsActivity", "SettingsActivity.onCreate() - age: "+age);
                    Log.i("SettingsActivity", "SettingsActivity.onCreate() - live in Nursing: "+liveInNursing+" visitingFacility: "+visitingFacility);
                    Log.i("SettingsActivity", "SettingsActivity.onCreate() - get resulting health conditions: "+Arrays.toString(healthConditions));
                    // TODO: Upload users personal info at this point!!
                }
            }
        });
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_lung:
                if (checked) {
                    healthConditions[0] = true;
                }
                else {
                    healthConditions[0] = false;
                }
                break;
            case R.id.checkbox_diabetes:
                if (checked) {
                    healthConditions[1] = true;
                }
                else {
                    healthConditions[1] = false;
                }
                break;
            case R.id.checkbox_neurologic:
                if (checked) {
                    healthConditions[2] = true;
                }
                else {
                    healthConditions[2] = false;
                }
                break;
            case R.id.checkbox_heart:
                if (checked) {
                    healthConditions[3] = true;
                }
                else {
                    healthConditions[3] = false;
                }
                break;
            case R.id.checkbox_dialysis:
                if (checked) {
                    healthConditions[4] = true;
                }
                else {
                    healthConditions[4] = false;
                }
                break;
            case R.id.checkbox_liver:
                if (checked) {
                    healthConditions[5] = true;
                }
                else {
                    healthConditions[5] = false;
                }
                break;
            case R.id.checkbox_preg:
                if (checked) {
                    healthConditions[6] = true;
                }
                else {
                    healthConditions[6] = false;
                }
                break;
            case R.id.checkbox_obese:
                if (checked) {
                    healthConditions[7] = true;
                }
                else {
                    healthConditions[7] = false;
                }
                break;
        }
    }
}
