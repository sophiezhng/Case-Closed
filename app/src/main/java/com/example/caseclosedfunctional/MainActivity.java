package com.example.caseclosedfunctional;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
<<<<<<< Updated upstream
=======

import com.google.firebase.auth.FirebaseAuth;
>>>>>>> Stashed changes

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

<<<<<<< Updated upstream

}
=======
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, Login.class));
        finish();
    }
}

/*STUFF TO-DO:
- FIX PASSWORD FOR LOGIN AND NAME FOR REGISTER
- GET VOICEFLOW CONNECTED TO THE FIREBASE DATABASE
- GET THE DASHBOARD AND CALENDAR SET UP
 */
>>>>>>> Stashed changes
