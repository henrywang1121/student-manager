package edu.bu.www.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UCAdmissionActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home1:
                Intent intent = new Intent(UCAdmissionActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucadmission);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.ucadmissionLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        Button ucwebsiteButton = (Button) findViewById(R.id.ucwebsiteButton);
        ucwebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.universityofcalifornia.edu"));
                startActivity(intent);
            }
        });

        ImageButton berkeleyImageButton = (ImageButton) findViewById(R.id.berkeleyImageButton);
        berkeleyImageButton.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                 public boolean onLongClick(View view){
                    String berkeleyInfo = "The University of California, Berkeley (also referred to as UC Berkeley, " +
                            "Berkeley, and Cal) is a public research university located in Berkeley, California. " +
                            "Founded in 1868, Berkeley is the oldest of the ten research universities affiliated " +
                            "with the University of California system and is ranked as one of the leading research " +
                            "universities in the world and the top public university in the United States.";

                    Toast.makeText(UCAdmissionActivity.this, berkeleyInfo, Toast.LENGTH_LONG).show();

                    return true;
            }

        });


        TextView requirementsTextView = (TextView) findViewById(R.id.requirementsTextView);

        String requirementsText="\t\t\tUC freshmen come from all over the world, from every culture and ethnicity" +
                " and from across the economic spectrum. Every year, they arrive on our campuses nearly 40,000" +
                " strong — ready for challenge and discovery.So, who do we consider to be a prospective freshman?" +
                " You are a freshman applicant if you are currently in high school or have graduated from high school," +
                " but not enrolled in a regular session at a college or university after high school graduation." +
                " While we recognize that each student is unique, with different strengths and life experiences," +
                " all prospective freshmen need to display academic rigor and meet the same minimum requirements" +
                " to be considered for admission.\n\n" +

                "\t\t\tAs we consider each individual application - and rest assured, we do consider each one - we look beyond" +
                " grades and test scores. We spend time evaluating your academic achievements in light of the" +
                " opportunities available to you and your demonstrated capacity to contribute to the intellectual life at UC.\n\n" +

                "\t\t\tThe 14 factors we weigh are: \n\n" +

                "1.\tAcademic grade point average in all completed \"a-g\" courses, including additional points for completed" +
                " UC-certified honors courses.\n\n" +
                "2.\tScores on the following tests: ACT with Writing or the SAT Reasoning Test.\n\n" +
                "3.\tNumber of, content of and performance in academic courses beyond the minimum \"a-g\" requirements.\n\n" +
                "4.\tNumber of and performance in UC-approved honors, Advanced Placement, International Baccalaureate" +
                " Higher Level and transferable college courses.\n\n" +
                "5.\tIdentification by UC as being ranked in the top 9 percent of your high school class at the end of" +
                " your junior year.\n\n" +
                "6.\tQuality of your senior-year program as measured by the type and number of academic courses in progress" +
                " or planned.\n\n" +
                "7.\tQuality of your academic performance relative to the educational opportunities available in your" +
                " high school.\n\n" +
                "8.\tOutstanding performance in one or more specific subject areas.\n\n" +
                "9.\tOutstanding work in one or more special projects in any academic field of study.\n\n" +
                "10.\tRecent, marked improvement in academic performance as demonstrated by academic GPA and the quality" +
                " of coursework completed or in progress.\n\n" +
                "11.\tSpecial talents, achievements and awards in a particular field, such as visual and performing arts," +
                " communication or athletic endeavors; special skills, such as demonstrated written and oral proficiency" +
                " in other languages; special interests, such as intensive study and exploration of other cultures;" +
                " experiences that demonstrate unusual promise for leadership, such as significant community service" +
                " or significant participation in student government; or other significant experiences or achievements" +
                " that demonstrate the student's promise for contributing to the intellectual vitality of a campus.\n\n" +
                "12.\tCompletion of special projects undertaken in the context of your high school curriculum or in conjunction" +
                " with special school events, projects or programs.\n\n" +
                "13.\tAcademic accomplishments in light of your life experiences and special circumstances, including" +
                " but not limited to: disabilities, low family income, first generation to attend college, need" +
                " to work, disadvantaged social or educational environment, difficult personal and family situations" +
                " or circumstances, refugee status or veteran status.\n\n" +
                "14.\tLocation of your secondary school and residence.";

        requirementsTextView.setText(requirementsText);
    }


    private static final String TAG = "StateChange";

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "UCAdmissionActivity onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "UCAdmissionActivity onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "UCAdmissionActivity onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "UCAdmissionActivity onStop");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "UCAdmissionActivity onRestart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "UCAdmissionActivity onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "UCAdmissionActivity onSaveInstanceState");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "UCAdmissionActivity onRestoreInstanceState");
    }
}
