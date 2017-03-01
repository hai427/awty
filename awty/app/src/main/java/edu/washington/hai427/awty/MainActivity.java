package edu.washington.hai427.awty;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText message;
    EditText number;
    EditText minutes;
    Button start;
    AlarmManager alarmManager;

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
    }
}
