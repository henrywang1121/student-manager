package edu.bu.www.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UCListActivity extends AppCompatActivity {

    ListView ucList;
    private String[] uc ={"UC Berkeley", "UC Los Angeles", "UC San Diego",
                        "UC Davis", "UC Irvine", "UC Santa Barbara", "UC Santa Cruz",
                        "UC Riverside", "UC Merced"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uclist);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.uclistLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        ucList = (ListView) findViewById(R.id.uclistLView);
        ArrayAdapter<String> ucListArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        ucList.setAdapter(ucListArrayAdapter);

        new UCListTask().execute();

        ucList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch(i){
                    case 0: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://www.berkeley.edu/"));
                            startActivity(intent);
                            break;
                    case 1: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://www.ucla.edu/"));
                            startActivity(intent);
                            break;
                    case 2: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://ucsd.edu/"));
                            startActivity(intent);
                            break;
                    case 3: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.ucdavis.edu/"));
                            startActivity(intent);
                            break;
                    case 4: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://uci.edu/"));
                            startActivity(intent);
                            break;
                    case 5: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.ucsb.edu/"));
                            startActivity(intent);
                            break;
                    case 6: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.ucsc.edu/"));
                            startActivity(intent);
                            break;
                    case 7: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://www.ucr.edu/"));
                        startActivity(intent);
                            break;
                    case 8: intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://www.ucmerced.edu/"));
                            startActivity(intent);
                            break;
                }
            }
        });
    }

    public class UCListTask extends AsyncTask<Void, String, Void>{
        ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
            adapter= (ArrayAdapter<String>) ucList.getAdapter();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item: uc){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(item);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(UCListActivity.this, "Completed", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(UCListActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
