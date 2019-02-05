package edu.bu.www.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static edu.bu.www.studentmanager.R.id.loginLayout;
import static edu.bu.www.studentmanager.R.layout.activity_login;

public class LoginActivity extends AppCompatActivity {

    private EditText loginName;
    private EditText loginPassword;
    private TextView attemptInfo;
    private Button loginButton;
    private int passwordCounter = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(loginLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }


        loginName = (EditText) findViewById(R.id.loginName);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        attemptInfo = (TextView) findViewById(R.id.attemptInfo);
        attemptInfo.setText("No of attempts remaining: 10");
        loginButton = (Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(loginName.getText().toString(), loginPassword.getText().toString());
            }
        });

    }

    private void validate(String username, String password){
        if((username.equals("henry")) && (password.equals("123"))){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else{
            passwordCounter--;
            attemptInfo.setText("No of attempts remaining: " + String.valueOf(passwordCounter));
            if(passwordCounter == 0)
                loginButton.setEnabled(false);
        }
    }
}