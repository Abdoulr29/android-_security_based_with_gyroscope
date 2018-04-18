package com.android.dev.gyroscope;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView x_axis, y_axis, z_axis;
    private MediaPlayer player;
    private SensorManager sensorManager;
    private Sensor gyros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x_axis = findViewById(R.id.x);
        y_axis = findViewById(R.id.y);
        z_axis = findViewById(R.id.z);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyros = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, gyros, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void sendSMS(String phoneNumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        Toast.makeText(MainActivity.this, "message sent", Toast.LENGTH_SHORT).show();
    }

    public void alertSound() {
        player = MediaPlayer.create(this, R.raw.alarm);
        player.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float sum = x + y + z;
            if (sum >= 2) {
                sendSMS("+250788835642", "someone is knocking your door musaza!!!");
                alertSound();
            }

        }
        x_axis.setText("x :" + event.values[0]);
        y_axis.setText("x :" + event.values[1]);
        z_axis.setText("x :" + event.values[2]);
    }
}

