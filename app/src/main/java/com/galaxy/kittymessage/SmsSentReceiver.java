package com.galaxy.kittymessage;

import static android.app.Activity.RESULT_OK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SmsSentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() == RESULT_OK) {
            // SMS sent successfully

        } else {
            // SMS sending failed
            Toast.makeText(context, "SMS sending failed", Toast.LENGTH_SHORT).show();
        }
    }
}