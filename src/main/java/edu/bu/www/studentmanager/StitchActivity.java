package edu.bu.www.studentmanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

public class StitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stitch);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.stitchLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }
    }
}
