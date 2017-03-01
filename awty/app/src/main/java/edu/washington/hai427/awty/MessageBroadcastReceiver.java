package edu.washington.hai427.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Hai on 3/1/2017.
 */

public class MessageBroadcastReceiver extends BroadcastReceiver {
    private String number;
    private String message;

    private static final String TAG = "MessageBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        SmsManager smsManager = SmsManager.getDefault();
        Log.i(TAG, "onReceive: Text sent!");
        number = intent.getStringExtra("Phone");
        message = intent.getStringExtra("Message");

        Toast.makeText(context, number + ": " + message.toString(), Toast.LENGTH_SHORT).show();

        smsManager.sendTextMessage(number, null, message, null, null);
    }
}
