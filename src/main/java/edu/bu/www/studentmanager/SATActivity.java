package edu.bu.www.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SATActivity extends AppCompatActivity {


    int resultSAT = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home1:
                Intent intent = new Intent(SATActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "SAT onCreate");
        setContentView(R.layout.activity_sat);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.satLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        Button calculateSATButton = (Button) findViewById(R.id.calculateSATButton);
        calculateSATButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText readScoreEditText = (EditText) findViewById(R.id.readScoreEditText);
                EditText mathScoreEditText = (EditText) findViewById(R.id.mathScoreEditText);
                TextView resultSATTextView = (TextView) findViewById(R.id.resultSATTextView);


                int readingScore = 0;
                if (!(readScoreEditText.getText().toString()).equals(""))
                    readingScore = Integer.parseInt(readScoreEditText.getText().toString());

                int mathScore = 0;
                if (!(mathScoreEditText.getText().toString()).equals(""))
                    mathScore = Integer.parseInt(mathScoreEditText.getText().toString());

                resultSAT = readingScore + mathScore;

                if ((readingScore + mathScore) == 0)
                    resultSATTextView.setText("Invalid Input");
                else
                    resultSATTextView.setText(resultSAT + "");
            }
        });


        Button switchGPAButton = (Button) findViewById(R.id.switchGPAButton);
        switchGPAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SATActivity.this, GPAActivity.class);
                finish();
            }
        });
    }


    public void finish() {
        Intent data = new Intent();
        data.putExtra("returnString", resultSAT+"");
        setResult(RESULT_OK, data);
        super.finish();
    }


    private static final String TAG = "StateChange";

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "SAT onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "SAT onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "SAT onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "SAT onStop");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "SAT onRestart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "SAT onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "SAT onSaveInstanceState");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "SAT onRestoreInstanceState");
    }
}
