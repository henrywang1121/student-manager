package edu.bu.www.studentmanager;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import edu.bu.www.studentmanager.database.DatabaseVariables.StudentDatabaseContent;


public class StudentListEditorActivity extends AppCompatActivity {

    private Spinner interestSpinner;
    private EditText nameEditText;
    private EditText schoolEditText;
    private EditText gradeEditText;
    private int interest = StudentDatabaseContent.interest_humanities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_editor);

        //Set the color based on the preference chosen by the user
        LinearLayout layout = (LinearLayout) findViewById(R.id.studentListEditorLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        nameEditText = (EditText) findViewById(R.id.edit_student_name);
        schoolEditText = (EditText) findViewById(R.id.edit_school);
        gradeEditText = (EditText) findViewById(R.id.edit_grade);
        interestSpinner = (Spinner) findViewById(R.id.spinner_interest);
        createInterestSpinner();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void createInterestSpinner() {

        ArrayAdapter interestArraySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_interest, android.R.layout.simple_spinner_item);
        interestArraySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        interestSpinner.setAdapter(interestArraySpinnerAdapter);
        interestSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.interest_sciences))) {
                        interest = StudentDatabaseContent.interest_sciences;
                    }
                    else if (selection.equals(getString(R.string.interest_engineering))) {
                        interest = StudentDatabaseContent.interest_engineering;
                    }
                    else {
                        interest = StudentDatabaseContent.interest_humanities;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                interest = StudentDatabaseContent.interest_humanities;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_studentlisteditor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                addStudent();
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addStudent() {

        String name = nameEditText.getText().toString();
        String school = schoolEditText.getText().toString();
        String gradeString = gradeEditText.getText().toString();
        int grade = Integer.parseInt(gradeString);

        ContentValues values = new ContentValues();
        values.put(StudentDatabaseContent.COLUMN_STUDENT_NAME, name);
        values.put(StudentDatabaseContent.COLUMN_SCHOOL, school);
        values.put(StudentDatabaseContent.COLUMN_GRADE, grade);
        values.put(StudentDatabaseContent.COLUMN_INTEREST, interest);

        Uri uri = getContentResolver().insert(StudentDatabaseContent.CONTENT_URI, values);
        if (uri == null) {
            Toast.makeText(this, getString(R.string.editor_student_added_failure), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.editor_student_added_success), Toast.LENGTH_SHORT).show();
        }
    }


}