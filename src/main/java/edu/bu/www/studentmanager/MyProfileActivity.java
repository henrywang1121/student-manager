package edu.bu.www.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class MyProfileActivity extends AppCompatActivity {

    private static String profileName;
    private static String email;
    private static String address;
    EditText profileNameEditText;
    EditText profileEmailEditText;
    EditText profileAddressEditText;
    private static CheckBox fullTimeCheckBox;
    private static CheckBox partTimeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        String[] position = {"Teacher", "Counselor", "Dean", "Admin"};
        final Spinner positionSpinner = (Spinner) findViewById(R.id.gradeSpinner);
        ArrayAdapter<String> positionSpinnerAdapter = new ArrayAdapter<String>(MyProfileActivity.this,
                android.R.layout.simple_list_item_1, position);
        positionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        positionSpinner.setAdapter(positionSpinnerAdapter);

        //Set the color based on the preference chosen by the user
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.myProfileLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }


        SharedPreferences mySharedPreferences2 = getSharedPreferences("Profile", MODE_PRIVATE);

        profileNameEditText = (EditText) findViewById(R.id.profileNameEditText);
        profileNameEditText.setText(mySharedPreferences2.getString("profileName", ""));

        profileEmailEditText = (EditText) findViewById(R.id.profileEmailEditText);
        profileEmailEditText.setText(mySharedPreferences2.getString("email", ""));

        profileAddressEditText = (EditText) findViewById(R.id.profileAddressEditText);
        profileAddressEditText.setText(mySharedPreferences2.getString("address", ""));

        fullTimeCheckBox = (CheckBox) findViewById(R.id.fullTimeCheckBox);
        fullTimeCheckBox.setChecked(mySharedPreferences2.getBoolean("fullTime", false));

        partTimeCheckBox = (CheckBox) findViewById(R.id.partTimeCheckBox);
        partTimeCheckBox.setChecked(mySharedPreferences2.getBoolean("partTime", false));

        positionSpinner.setSelection(mySharedPreferences2.getInt("positionSpinner", 0));


        Button profileSaveButton = (Button) findViewById(R.id.profileSaveButton);
        profileSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    profileName = profileNameEditText.getText().toString();
                    email = profileEmailEditText.getText().toString();
                    address = profileAddressEditText.getText().toString();

                    saveInfo(profileName, email, address,
                            fullTimeCheckBox.isChecked(), partTimeCheckBox.isChecked(),
                            positionSpinner.getSelectedItemPosition());
            }
        });
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
                Intent intent = new Intent(MyProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void saveInfo(String nameParam, String emailParam, String addressParam, boolean fullTimeParam, boolean partTimeParam, int positionSpinnerParam){

        SharedPreferences mySharedPreferences = getSharedPreferences("Profile", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
        myEditor.putString("profileName", nameParam);
        myEditor.putString("email", emailParam);
        myEditor.putString("address", addressParam);
        myEditor.putBoolean("fullTime", fullTimeParam);
        myEditor.putBoolean("partTime", partTimeParam);
        myEditor.putInt("positionSpinner", positionSpinnerParam);
        myEditor.commit();



    }


    public String getProfileName(){
        SharedPreferences mySharedPreferences = getSharedPreferences("Profile", MODE_PRIVATE);
        String name = mySharedPreferences.getString("profileName", "");
        return name;
    }

    public String getEmail(){
        SharedPreferences mySharedPreferences = getSharedPreferences("Profile", MODE_PRIVATE);
        String email = mySharedPreferences.getString("email", "");
        return email;
    }

    public String getAddress(){
        SharedPreferences mySharedPreferences = getSharedPreferences("Profile", MODE_PRIVATE);
        String address = mySharedPreferences.getString("address", "");
        return address;
    }


    private static final String TAG = "StateChange";

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "Profile onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "Profile onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "Profile onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "Profile onStop");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "Profile onRestart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "Profile onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "Profile onSaveInstanceState");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "Profile onRestoreInstanceState");
    }


}
