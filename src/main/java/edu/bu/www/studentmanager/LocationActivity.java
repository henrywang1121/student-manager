package edu.bu.www.studentmanager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    LocationManager locationManager;
    Button locateButton;
    Button mapButton;
    Button networkButton;
    TextView networkTextView;
    TextView locationTextView;
    double latitude;
    double longitude;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.locationLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)) {
            layout.setBackgroundColor(selectedColor);
        }

        connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        networkTextView = (TextView) findViewById(R.id.networkTextView);
        networkButton = (Button) findViewById(R.id.networkButton);
        networkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networkTextView.setText("Connection = "+(networkInfo != null && networkInfo.isConnectedOrConnecting()));
            }
        });



        locateButton = (Button) findViewById(R.id.locateButton);
        locationTextView = (TextView) findViewById(R.id.coordinatesTextView);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        ActivityCompat.requestPermissions(LocationActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        locateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GPS gps = new GPS(getApplicationContext());
                Location location = gps.getLocation();
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                locationTextView.setText("Latitude=" + latitude + " Longitude=" + longitude);
            }
        });

        mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+latitude+","+longitude));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }

    class GPS implements LocationListener{

        Context context;

        public GPS(Context contextParam) {
            this.context = contextParam;
        }


        public Location getLocation(){
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

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
                Intent intent = new Intent(LocationActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
