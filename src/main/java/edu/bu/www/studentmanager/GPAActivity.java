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
import android.widget.Toast;

public class GPAActivity extends AppCompatActivity {

    int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "GPA onCreate");
        setContentView(R.layout.activity_gpa);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.gpaLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        Button calculateGPAButton = (Button) findViewById(R.id.calculateGPAButton);
        calculateGPAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText aSemEditText = (EditText) findViewById(R.id.aSemEditText);
                EditText bSemEditText = (EditText) findViewById(R.id.bSemEditText);
                EditText cSemEditText = (EditText) findViewById(R.id.cSemEditText);
                EditText dSemEditText = (EditText) findViewById(R.id.dSemEditText);
                EditText fSemEditText = (EditText) findViewById(R.id.fSemEditText);
                TextView resultGPATextView = (TextView) findViewById(R.id.resultGPATextView);

                double numOfA = 0;
                if (!(aSemEditText.getText().toString()).equals(""))
                        numOfA = Integer.parseInt(aSemEditText.getText().toString());
                double numOfB = 0;
                if (!(bSemEditText.getText().toString()).equals(""))
                    numOfB = Integer.parseInt(bSemEditText.getText().toString());
                double numOfC = 0;
                if (!(cSemEditText.getText().toString()).equals(""))
                    numOfC = Integer.parseInt(cSemEditText.getText().toString());
                double numOfD = 0;
                if (!(dSemEditText.getText().toString()).equals(""))
                    numOfD = Integer.parseInt(dSemEditText.getText().toString());
                double numOfF = 0;
                if (!(fSemEditText.getText().toString()).equals(""))
                    numOfF = Integer.parseInt(fSemEditText.getText().toString());

                double resultGPA = (numOfA*4 + numOfB*3 + numOfC*2 + numOfC*1)/
                                    (numOfA + numOfB + numOfC + numOfD + numOfF);
                if ((numOfA*4 + numOfB*3 + numOfC*2 + numOfC*1)==0)
                    resultGPATextView.setText("Invalid Input");
                else
                    resultGPATextView.setText(resultGPA + "");
            }
        });

        Button switchSATButton = (Button) findViewById(R.id.switchSATButton);
        switchSATButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GPAActivity.this, SATActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        String returnString = "";
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            if (data.hasExtra("returnString")) { returnString =
                    data.getExtras().getString("returnString");
            } }
        String displayReturnString = "Your SAT = " + returnString;
        Toast.makeText(GPAActivity.this, displayReturnString, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home1:
                Intent intent = new Intent(GPAActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String TAG = "StateChange";

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "GPA onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "GPA onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "GPA onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "GPA onStop");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "GPA onRestart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "GPA onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "GPA onSaveInstanceState");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "GPA onRestoreInstanceState");
    }

}
