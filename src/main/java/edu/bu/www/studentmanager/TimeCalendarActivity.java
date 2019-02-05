package edu.bu.www.studentmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.bu.www.studentmanager.TimeBoundService.MyLocalBinder;


public class TimeCalendarActivity extends AppCompatActivity {

    TimeBoundService myService;
    boolean isBound = false;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String string = bundle.getString("dateKey");
            TextView myTextView = (TextView)findViewById(R.id.dateTextView);
            myTextView.setText(string);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_calendar);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.timeCalendarLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        Intent intent = new Intent(this, TimeBoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            TimeBoundService.MyLocalBinder binder = (MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    protected void time(View view){

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String currentTime = myService.getCurrentTime();
                                TextView timeTextView = (TextView) findViewById(R.id.timeTextView);
                                timeTextView.setText(currentTime);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }

    public void date(View view) {
        Runnable runnable = new Runnable() {
            public void run() {
                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                String dateString = dateformat.format(new Date());
                bundle.putString("dateKey", dateString);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        };
        Thread myThread = new Thread(runnable);
        myThread.start();
    }

    public void alarm(View view){

        final int seconds = 5;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent alarmIntent = new Intent(TimeCalendarActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(TimeCalendarActivity.this, 0, alarmIntent, 0);
                long alarmTime = (System.currentTimeMillis() + 1000*seconds);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
            }
        };
        Thread myThread = new Thread(runnable);
        myThread.start();

        new CountDownTimer(1000*seconds, 1000) {
            TextView countdownTextView = (TextView) findViewById(R.id.countdownTextView);

            public void onTick(long millisUntilFinished) {
                countdownTextView.setText("Seconds Remain: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countdownTextView.setText("Alarm!");
            }
        }.start();
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
                Intent intent = new Intent(TimeCalendarActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}