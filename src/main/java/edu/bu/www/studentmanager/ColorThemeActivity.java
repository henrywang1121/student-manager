package edu.bu.www.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ColorThemeActivity extends AppCompatActivity {

    LinearLayout colorLayout;
    Button redColorButton;
    Button greenColorButton;
    Button yellowColorButton;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_theme);

        colorLayout = (LinearLayout) findViewById(R.id.colorLayout);

        if (getColorTheme() != getResources().getColor(R.color.colorPrimary)){
            colorLayout.setBackgroundColor(getColorTheme());
        }

        redColorButton = (Button) findViewById(R.id.buttonRed);
        redColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorLayout.setBackgroundColor(getResources().getColor(R.color.colorRed));
                saveColorTheme(getResources().getColor(R.color.colorRed));
            }});

        greenColorButton = (Button) findViewById(R.id.buttonGreen);
        greenColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                saveColorTheme(getResources().getColor(R.color.colorGreen));
            }});

        yellowColorButton= (Button) findViewById(R.id.buttonYellow);
        yellowColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorLayout.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                saveColorTheme(getResources().getColor(R.color.colorYellow));
            }});

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ColorThemeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    public void saveColorTheme(int color){
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
        myEditor.putInt("color", color);
        myEditor.commit();
    }

    public int getColorTheme(){
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        return selectedColor;
    }

}
