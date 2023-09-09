package com.galaxy.kittymessage;

import static android.app.Activity.RESULT_OK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SmsDeliveryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() == RESULT_OK) {
            // SMS sent successfully
            Toast.makeText(context, "SMS delivery", Toast.LENGTH_SHORT).show();
        } else {
            // SMS sending failed
            Toast.makeText(context, "SMS delivery failed", Toast.LENGTH_SHORT).show();
        }
    }
}