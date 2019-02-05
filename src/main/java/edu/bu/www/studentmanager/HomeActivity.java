package edu.bu.www.studentmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity {

    private Button homeButton2;
    private Button homeButton3;
    private Button homeButton4;
    private Button homeButton5;
    private Button homeButton6;
    private Button homeButton7;
    private Button homeButton8;
    private Button homeButton9;
    private Button homeButton10;
    private Button homeButton11;

    Notification.Builder notification;
    private static final int notificationID = 1;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    WifiManager wifiManager;
    ToggleButton wifiButton;

    MediaPlayer mediaPlayer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_profile:
                Intent intent = new Intent(HomeActivity.this, MyProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_start_music:
                mediaPlayer = MediaPlayer.create(HomeActivity.this, R.raw.music);
                mediaPlayer.start();
                return true;
            case R.id.action_stop_music:
                if (mediaPlayer != null){
                    mediaPlayer.reset();
                    mediaPlayer.release();
                }
                return true;
            case R.id.action_broadcast:
                Intent intent2 = new Intent();
                intent2.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent2.setAction("edu.bu.www.studentmanager");
                sendBroadcast(intent2);
                return true;
            case R.id.action_notify:
                //Notification
                notification = new Notification.Builder(this);
                notification.setAutoCancel(true);
                notification.setContentTitle("Title: New Message");
                notification.setContentText("Body: There is a new video");
                notification.setSmallIcon(R.mipmap.ic_launcher);

                Intent intent3 = new Intent(this, YoutubeActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);

                //Notification Manager
                NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    notificationManager.notify(notificationID, notification.build());
                }
                return true;
            case R.id.action_exit:
                //Alert
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder
                        .setTitle("Alert!")
                        .setMessage("Return or Exit Student Manager")
                        .setCancelable(false)
                        .setNegativeButton("Return",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                }
                        )
                        .setPositiveButton("Exit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        System.exit(0);
                                    }
                                }
                        );
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
        }
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Wifi
        wifiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
        wifiButton = (ToggleButton) findViewById(R.id.wifiButton);
        wifiButton.setTextOn("Wifi On");
        wifiButton.setTextOff("Wifi Off");

        if(wifiManager.isWifiEnabled())
            wifiButton.setChecked(true);
        else
            wifiButton.setChecked(false);

        wifiButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if(on)
                    wifiManager.setWifiEnabled(true);
                else
                    wifiManager.setWifiEnabled(false);
            }
        });



        //Navigation Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.navigation_email_me) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello Hello World");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);}
                }
                if (id == R.id.navigation_about_me) {
                    Intent intent = new Intent(HomeActivity.this, AboutMeActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });


        //Set the color based on the preference chosen by the user
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.homeLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }


        //Buttons

        homeButton2 = (Button) findViewById(R.id.homeButton2);
        homeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+1234567890));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        homeButton3 = (Button) findViewById(R.id.homeButton3);
        homeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        homeButton4 = (Button) findViewById(R.id.homeButton4);
        homeButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TeacherListActivity.class);
                startActivity(intent);
            }
        });

        homeButton5 = (Button) findViewById(R.id.homeButton5);
        homeButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });

        homeButton6 = (Button) findViewById(R.id.homeButton6);
        homeButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GPAActivity.class);
                startActivity(intent);

            }
        });

        homeButton7 = (Button) findViewById(R.id.homeButton7);
        homeButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UCAdmissionActivity.class);
                startActivity(intent);
            }
        });

        homeButton8 = (Button) findViewById(R.id.homeButton8);
        homeButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TabFragmentActivity.class);
                startActivity(intent);
            }
        });

        homeButton9 = (Button) findViewById(R.id.homeButton9);
        homeButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TimeCalendarActivity.class);
                startActivity(intent);
            }
        });

        homeButton10 = (Button) findViewById(R.id.homeButton10);
        homeButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });

        homeButton11 = (Button) findViewById(R.id.homeButton11);
        homeButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UCListActivity.class);
                startActivity(intent);
            }
        });

    }
}