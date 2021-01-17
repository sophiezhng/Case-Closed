package com.example.caseclosedfunctional;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.applandeo.materialcalendarview.CalendarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int streakDays;
    ArrayList<String> messageTextViews= new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTextViews.add("Practice social distancing!");
        messageTextViews.add("Remember to wear a mask whenever you go outside!");
        messageTextViews.add("Avoid large social gatherings.");

        LinearLayout linearLayout= findViewById(R.id.linearlayout);
        Toolbar toolBar = findViewById(R.id.toolbar);
        toolBar.setTitle(getResources().getString(R.string.my_dashboard));

        ProgressBar progressBar = findViewById(R.id.progress_bar);

        CalendarView calendarView = findViewById(R.id.calendar_view);

        TextView streak = findViewById(R.id.num_of_days);
        streakDays = 3;
        if (streakDays > 0) {
            int fireUnicode = 0x1F525;
            streak.setText(streakDays +getEmojiByUnicode(fireUnicode));
            if (streakDays%7 != 0) {
                progressBar.setProgress((int) (streakDays%7/0.07), true);
            }
            else {
                progressBar.setProgress(100, true);
            }
        }
        else {
            streak.setText(String.valueOf(streakDays));
            progressBar.setProgress(0, true);
        }
        ListView listView = findViewById(R.id.list_view);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.message_row, R.id.message_row, messageTextViews);
        listView.setAdapter(arrayAdapter);

    }
    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}

//public void logout(View view) {
//   FirebaseAuth.getInstance().signOut();
//   startActivity(new Intent(MainActivity.this, Login.class));
//   finish();
//}