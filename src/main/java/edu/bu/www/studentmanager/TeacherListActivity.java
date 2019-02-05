package edu.bu.www.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TeacherListActivity extends AppCompatActivity {

    private List<Teacher> myTeachers = new ArrayList<Teacher>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        //Set the color based on the preference chosen by the user
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.teacherListLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        createTeacherList();
        createTeacherListView();
        registerClickCallBack();
    }

    public void createTeacherList(){

        myTeachers.add(new Teacher("Roger Federer", "Teacher", R.drawable.teachericon1, "Apple High School"));
        myTeachers.add(new Teacher("Michael Jordan", "Teacher", R.drawable.teachericon2, "Orange High School"));
        myTeachers.add(new Teacher("Issac Newton", "Dean", R.drawable.teachericon3, "Banana High School"));
        myTeachers.add(new Teacher("John Nash", "Coach", R.drawable.teachericon4, "Grape High School"));
        myTeachers.add(new Teacher("Albert Einstein", "Teacher", R.drawable.teachericon5, "Apple High School"));
        myTeachers.add(new Teacher("Rosa Franklin", "Teacher", R.drawable.teachericon6, "Orange High School"));
        myTeachers.add(new Teacher("Nikola Tesla", "Principal", R.drawable.teachericon7, "Banana High School"));
        myTeachers.add(new Teacher("Max Planck", "Counselor", R.drawable.teachericon8, "Grape High School"));
        myTeachers.add(new Teacher("Marie Curie", "Dean", R.drawable.teachericon9, "Apple High School"));
        myTeachers.add(new Teacher("James Watson", "Teacher", R.drawable.teachericon10, "Orange High School"));


    }

    public void createTeacherListView(){
        ArrayAdapter<Teacher> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.teacherListView);
        list.setAdapter(adapter);

    }

    private void registerClickCallBack() {
        ListView list = (ListView) findViewById(R.id.teacherListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                Teacher clickedTeacher = myTeachers.get(position);
                String message = "You clicked faculty #" + (position+1)
                        + " whose name is " + clickedTeacher.getTeacherName();
                Toast.makeText(TeacherListActivity.this, message, Toast.LENGTH_LONG).show();

            }
        });

    }

    private class MyListAdapter extends ArrayAdapter<Teacher> {
        public MyListAdapter(){
            super(TeacherListActivity.this, R.layout.teacher_view, myTeachers);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View teacherView = convertView;
            if (teacherView == null) {
                teacherView = getLayoutInflater().inflate(R.layout.teacher_view, parent, false);
            }


            //Find the teacher to work with
            Teacher currentTeacher = myTeachers.get(position);

            //Fill the view
            ImageView imageView = (ImageView) teacherView.findViewById(R.id.teacher_icon);
            imageView.setImageResource(currentTeacher.getIconId());

            //Teacher Name
            TextView teacherNameText = (TextView) teacherView.findViewById(R.id.teacherNameTextView);
            teacherNameText.setText(currentTeacher.getTeacherName());

            //Grade
            TextView gradeText = (TextView) teacherView.findViewById(R.id.gradeTextView);
            gradeText.setText(currentTeacher.getPosition());

            //High School
            TextView highSchoolText = (TextView) teacherView.findViewById(R.id.highSchoolTextView);
            highSchoolText.setText(currentTeacher.getHighSchool());

            return teacherView;
        }

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
                Intent intent = new Intent(TeacherListActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private static final String TAG = "StateChange";

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "TeacherListActivity onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "TeacherListActivity onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "TeacherListActivity onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "TeacherListActivity onStop");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "TeacherListActivity onRestart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "TeacherListActivity onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "TeacherListActivity onSaveInstanceState");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "TeacherListActivity onRestoreInstanceState");
    }

}
