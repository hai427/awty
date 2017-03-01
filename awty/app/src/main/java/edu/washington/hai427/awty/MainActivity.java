package edu.washington.hai427.awty;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText message;
    EditText number;
    EditText minutes;
    Button start;
    AlarmManager alarmManager;

    private Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Activity mainActivity = MainActivity.this;

        message = (EditText) findViewById(R.id.messageText);
        number = (EditText) findViewById(R.id.numberText);
        minutes = (EditText) findViewById(R.id.minutesText);
        start = (Button) findViewById(R.id.start);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mainActivity.checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                Log.i("TAG", "Permission to send SMS not granted");

                mainActivity.requestPermissions(new String[] {
                        Manifest.permission.SEND_SMS }, 1);
            }
        }

        message.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!(message.getText().toString() == null) && !(message.getText().toString().trim().isEmpty())) {
                    Log.v("TAG", message.getText().toString());
                    flag = true;
                } else {
                    Log.v("TAG", "Empty");
                    flag = false;
                }
                return false;
            }
        });

        number.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!(number.getText().toString() == null) && !(number.getText().toString().trim().isEmpty())) {
                    Log.v("TAG", number.getText().toString());
                    flag = true;
                } else {
                    Log.v("TAG", "Empty");
                    flag = false;
                }
                return false;
            }
        });

        minutes.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!(minutes.getText().toString() == null) && !(minutes.getText().toString().trim().isEmpty())) {
                    Log.v("TAG", minutes.getText().toString());
                    flag = true;
                } else {
                    Log.v("TAG", "Empty");
                    flag = false;
                }
                return false;
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!message.getText().equals("") && !number.getText().equals("") && !minutes.getText().equals("")) {

                    Intent intent = new Intent(getApplicationContext(), MessageBroadcastReceiver.class);

                    intent.putExtra("Message", message.getText().toString());
                    intent.putExtra("Phone", number.getText().toString());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

                    if (start.getText().equals("Start")) {
                        start.setText("Stop");
                        int val = Integer.parseInt(minutes.getText().toString());
                        sendMessage(val, alarmManager, pendingIntent);
                    } else {
                        start.setText("Start");
                        alarmManager.cancel(pendingIntent);
                    }
                }
            }
        });
    }

    public void sendMessage(int minutes, AlarmManager alarmManager, PendingIntent pendingIntent) {
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 1000, 60000 * minutes, pendingIntent);
    }
}
