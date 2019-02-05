package edu.bu.www.studentmanager;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeBoundService extends Service {

    private final IBinder myBinder = new MyLocalBinder();

    public TimeBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return (dateFormat.format(new Date()));
    }

    public class MyLocalBinder extends Binder {
        TimeBoundService getService(){return TimeBoundService.this;}
    }
}

