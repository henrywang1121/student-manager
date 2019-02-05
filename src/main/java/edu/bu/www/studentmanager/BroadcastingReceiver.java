package edu.bu.www.studentmanager;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastingReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Welcome: " + intent.getAction(), Toast.LENGTH_LONG).show();
    }

}
