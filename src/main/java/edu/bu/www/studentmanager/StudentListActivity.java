package edu.bu.www.studentmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.bu.www.studentmanager.database.DatabaseVariables.StudentDatabaseContent;


public class StudentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        //Set the color based on the preference chosen by the user
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.studentListLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.editorFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentListActivity.this, StudentListEditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        showStudentList2();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void showStudentList2() {

        String[] projection = {StudentDatabaseContent._ID, StudentDatabaseContent.COLUMN_STUDENT_NAME, StudentDatabaseContent.COLUMN_SCHOOL,
                StudentDatabaseContent.COLUMN_INTEREST, StudentDatabaseContent.COLUMN_GRADE};

        Cursor cursor = getContentResolver().query(
                StudentDatabaseContent.CONTENT_URI, projection, null, null, null);
        TextView studentlist2_text_view = (TextView) findViewById(R.id.text_view_student);

        studentlist2_text_view.setText("");
        toast("The Teacher table in the database has " + cursor.getCount() + " rows of students.");
        studentlist2_text_view.append("<"+ StudentDatabaseContent._ID + ">    <"
                + StudentDatabaseContent.COLUMN_STUDENT_NAME + ">    <"
                + StudentDatabaseContent.COLUMN_SCHOOL + ">    <"
                + StudentDatabaseContent.COLUMN_INTEREST + ">    <"
                + StudentDatabaseContent.COLUMN_GRADE + ">\n\n");

        int idColumnIndex = cursor.getColumnIndex(StudentDatabaseContent._ID);
        int nameColumnIndex = cursor.getColumnIndex(StudentDatabaseContent.COLUMN_STUDENT_NAME);
        int schoolColumnIndex = cursor.getColumnIndex(StudentDatabaseContent.COLUMN_SCHOOL);
        int interestColumnIndex = cursor.getColumnIndex(StudentDatabaseContent.COLUMN_INTEREST);
        int gradeColumnIndex = cursor.getColumnIndex(StudentDatabaseContent.COLUMN_GRADE);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(idColumnIndex);
            String name = cursor.getString(nameColumnIndex);
            String school = cursor.getString(schoolColumnIndex);
            int grade = cursor.getInt(gradeColumnIndex);
            int interest = cursor.getInt(interestColumnIndex);

            String interestString = "";
            if (interest == 0)
                interestString = "Humanities";
            if (interest == 1)
                    interestString = "Sciences";
            if (interest == 2)
                    interestString = "Engineering";

            studentlist2_text_view.append(("\n#" + id + ", " + name + ", " + school + ", " + interestString + ", " + grade + "th"));


        }
        cursor.close();
    }

    private void toast(String aToast){
        Toast.makeText(getApplicationContext(), aToast, Toast.LENGTH_LONG).show();
    }

    private void addStudent() {

        ContentValues values = new ContentValues();
        values.put(StudentDatabaseContent.COLUMN_STUDENT_NAME, "Henry Wang");
        values.put(StudentDatabaseContent.COLUMN_SCHOOL, "Banana High School");
        values.put(StudentDatabaseContent.COLUMN_INTEREST, StudentDatabaseContent.interest_sciences);
        values.put(StudentDatabaseContent.COLUMN_GRADE, 12);

        Uri uri = getContentResolver().insert(StudentDatabaseContent.CONTENT_URI, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_studentlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_default_student:
                addStudent();
                showStudentList2();
                return true;
            case R.id.action_home2:
                Intent intent = new Intent(StudentListActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
